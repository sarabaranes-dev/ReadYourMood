package it.uniroma3.siw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import it.uniroma3.siw.model.*;
import it.uniroma3.siw.service.*;

@Controller
public class AreaPersonaleController {

    @Autowired private CredenzialiService credenzialiService;
    @Autowired private LibroService libroService;
    @Autowired private OrdineService ordineService;
    @Autowired private RecensioneService recensioneService;

    // CASO D'USO: visualizzazione area personale
    @GetMapping("/area-personale")
    public String areaPersonale(Model model) {
        UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Credenziali cred = credenzialiService.findByUsername(ud.getUsername());
        Utente utente = cred.getUtente();

        // BLOCCO UTENTE: se bloccato, lo disconnetto e lo rimandio al login
        if (utente.getStato() == StatoUtente.BLOCCATO) {
            SecurityContextHolder.clearContext();
            return "redirect:/login?bloccato=true";
        }

        List<Long> ordiniRecensitiIds = recensioneService.getOrdiniRecensitiIds(utente);

        model.addAttribute("utente", utente);
        model.addAttribute("libriInVendita", libroService.findByVenditore(utente));
        model.addAttribute("ordini", ordineService.findByAcquirente(utente));
        model.addAttribute("ordiniRecensitiIds", ordiniRecensitiIds);
        model.addAttribute("recensioniRicevute", recensioneService.findByVenditore(utente));
        
        return "utente/area-personale";
    }
}