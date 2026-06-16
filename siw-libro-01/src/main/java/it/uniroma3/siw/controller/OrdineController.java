package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import it.uniroma3.siw.model.*;
import it.uniroma3.siw.service.*;
import java.util.List;

@Controller
public class OrdineController {

    @Autowired private OrdineService ordineService;
    @Autowired private LibroService libroService;
    @Autowired private CredenzialiService credenzialiService;

    private Utente getUtenteLoggato() {
        UserDetails ud = (UserDetails) SecurityContextHolder
            .getContext().getAuthentication().getPrincipal();
        return credenzialiService.findByUsername(ud.getUsername()).getUtente();
    }

    // 1. ACQUISTA SUBITO (singolo libro)
    @GetMapping("/libri/{id}/acquista")
    public String acquista(@PathVariable Long id, Model model) {
        Libro libro = libroService.findById(id);

        if (libro.getVenditore().getStato() == StatoUtente.BLOCCATO) {
            return "redirect:/libri/" + id + "?errore=venditore-bloccato";
        }

        if (libro.getStato() != StatoLibro.DISPONIBILE) {
            return "redirect:/libri/" + id;
        }

        //passo i dati al checkout (ID e dati)
        model.addAttribute("libriIds", List.of(id));
        model.addAttribute("libriScelti", List.of(libro));
        model.addAttribute("totale", libro.getPrezzo());
        
        return "ordini/checkout";
    }

    //mostra il form crea set
    @GetMapping("/libri/{libroId}/crea-set")
    public String showCreaSetDaLibro(@PathVariable Long libroId, Model model) {
        Libro libro = libroService.findById(libroId);
        Utente venditore = libro.getVenditore();
        List<Libro> libriVenditore = libroService.findDisponibiliByVenditore(venditore);
        
        model.addAttribute("venditore", venditore);
        model.addAttribute("libriVenditore", libriVenditore);
        return "ordini/crea-set";
    }

    // 2. CREA SET (più libri)
    @PostMapping("/ordini/crea-set")
    public String vaiAlCheckoutSet(@RequestParam List<Long> libriIds, Model model) {
        List<Libro> libri = libriIds.stream()
            .map(id -> libroService.findById(id))
            .filter(l -> l.getStato() == StatoLibro.DISPONIBILE)
            .toList();

        if (libri.isEmpty()) return "redirect:/libri";

        //calcolo somma dei prezzi
        double totale = libri.stream().mapToDouble(Libro::getPrezzo).sum();

        //passo i dati al checkout
        model.addAttribute("libriIds", libri.stream().map(Libro::getId).toList());
        model.addAttribute("libriScelti", libri);
        model.addAttribute("totale", totale);
        
        return "ordini/checkout";
    }

    // 3. CONFERMA FINALE, salvo nel Database
    @PostMapping("/ordini/conferma")
    public String confermaPagamento(@RequestParam List<Long> libriIds, Model model) {
        Utente acquirente = getUtenteLoggato();
        Ordine ordine;

        // Se è un solo libro, usa il metodo singolo
        if (libriIds.size() == 1) {
            Libro libro = libroService.findById(libriIds.get(0));
            ordine = ordineService.acquistaLibro(libro, acquirente);
        } else {
            // Se sono più libri, usa il metodo per il Set
            List<Libro> libri = libriIds.stream()
                .map(id -> libroService.findById(id))
                .toList();
            ordine = ordineService.creaSet(libri, acquirente);
        }

        model.addAttribute("ordine", ordine);
        return "ordini/conferma";
    }
}