package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import it.uniroma3.siw.model.*;
import it.uniroma3.siw.service.*;

@Controller
public class RecensioneController {

    @Autowired private RecensioneService recensioneService;
    @Autowired private OrdineService ordineService;
    @Autowired private CredenzialiService credenzialiService;

    // CASO D'USO: mostra form recensione
    @GetMapping("/ordini/{id}/recensisci")
    public String showForm(@PathVariable Long id, Model model) {
        Ordine ordine = ordineService.findById(id);
        model.addAttribute("ordine", ordine);
        model.addAttribute("recensione", new Recensione());
        return "recensioni/form";
    }

    // CASO D'USO: salva recensione
    @PostMapping("/ordini/{id}/recensisci")
    public String save(@PathVariable Long id, Recensione recensione) {
        UserDetails ud = (UserDetails) SecurityContextHolder
            .getContext().getAuthentication().getPrincipal();
        Utente acquirente = credenzialiService.findByUsername(ud.getUsername()).getUtente();
        Ordine ordine = ordineService.findById(id);
        recensioneService.save(recensione, acquirente, ordine);
        return "redirect:/area-personale";
    }
}