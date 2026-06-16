package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import it.uniroma3.siw.service.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired private UtenteService utenteService;
    @Autowired private LibroService libroService;

    // pannello admin
    @GetMapping
    public String pannello(Model model) {
        model.addAttribute("utenti", utenteService.findAll());
        return "admin/pannello";
    }

    // CASO D'USO: blocco/sblocco utente
    @GetMapping("/utenti/{id}/toggle")
    public String toggleUtente(@PathVariable Long id) {
        utenteService.toggleStato(id);
        return "redirect:/admin";
    }

    // CASO D'USO: eliminazione libro dalla bacheca
    @GetMapping("/libri/{id}/delete")
    public String deleteLibro(@PathVariable Long id) {
        libroService.deleteById(id);
        return "redirect:/libri";
    }
}