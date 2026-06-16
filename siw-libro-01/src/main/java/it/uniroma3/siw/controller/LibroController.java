package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import it.uniroma3.siw.model.*;
import it.uniroma3.siw.service.*;

@Controller
public class LibroController {

    @Autowired private LibroService libroService;
    @Autowired private UtenteService utenteService;
    @Autowired private RecensioneService recensioneService;
    @Autowired private OrdineService ordineService;

    // CASO D'USO: elenco libri disponibili
    @GetMapping("/libri")
    public String list(@RequestParam(required = false) String titolo,
                       @RequestParam(required = false) String autore,
                       @RequestParam(required = false) Integer anno,
                       @RequestParam(required = false) Integer valutazione,
                       @RequestParam(required = false) String emozione,  
                       @RequestParam(defaultValue = "0") int page,
                       Model model) {
        Page<Libro> libri;
        if (titolo != null && !titolo.isEmpty()) {
            libri = libroService.findByTitolo(titolo, page);
            model.addAttribute("cercaPerTitolo", titolo);
        } else if (autore != null && !autore.isEmpty()) {
            libri = libroService.findByAutore(autore, page);
            model.addAttribute("cercaPerAutore", autore);
        } else if (anno != null) {
            libri = libroService.findByAnno(anno, page);
            model.addAttribute("cercaPerAnno", anno);
        } else if (valutazione != null) {
            libri = libroService.findByValutazione(valutazione, page);
            model.addAttribute("cercaPerValutazione", valutazione);
        } else if (emozione != null && !emozione.isEmpty()) {  
            libri = libroService.findByEmozione(EmozioneLibro.valueOf(emozione), page);
            model.addAttribute("emozioneSelezionata", emozione);
        } else {
            libri = libroService.findDisponibili(page);
        }
        model.addAttribute("libri", libri.getContent());
        model.addAttribute("paginaCorrente", page);
        model.addAttribute("totalePagine", libri.getTotalPages());
        return "libri/list";
    }

    // CASO D'USO: dettaglio libro
    @GetMapping("/libri/{id}")
    public String show(@PathVariable Long id, Model model) {
        Libro libro = libroService.findById(id);
        model.addAttribute("libro", libro);
        return "libri/show";
    }

    // CASO D'USO: profilo pubblico venditore con recensioni
    @GetMapping("/venditori/{id}")
    public String profiloVenditore(@PathVariable Long id, Model model) {
        Utente venditore = utenteService.findById(id);
        model.addAttribute("venditore", venditore);

        // se bloccato mostra pagina apposita
        if (venditore.getStato() == StatoUtente.BLOCCATO) {
            return "venditori/profilo";  
        }

        model.addAttribute("libriPubblicati", libroService.findByVenditore(venditore));
        model.addAttribute("libriDisponibili", libroService.findDisponibiliByVenditore(venditore));
        model.addAttribute("ordiniAcquistati", ordineService.findByAcquirente(venditore));
        model.addAttribute("recensioni", recensioneService.findByVenditore(venditore));
        model.addAttribute("mediaVoti", recensioneService.getMediaVoti(venditore));
        return "venditori/profilo";
    }
    
    @GetMapping("/libri/cerca-emozione")
    public String cercaEmozione(Model model) {
        model.addAttribute("emozioni", EmozioneLibro.values());
        return "libri/cerca-emozione";
    }
    
    
}