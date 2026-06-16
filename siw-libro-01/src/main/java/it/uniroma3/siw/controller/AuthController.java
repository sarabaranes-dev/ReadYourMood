package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import it.uniroma3.siw.model.*;
import it.uniroma3.siw.service.*;

@Controller
public class AuthController {

    @Autowired private CredenzialiService credenzialiService;
    @Autowired private UtenteService utenteService;

    @GetMapping("/login")
    public String showLogin() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String showRegister(Model model) {
        model.addAttribute("utente", new Utente());
        model.addAttribute("credenziali", new Credenziali());
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(Utente utente, Credenziali credenziali) {
        this.utenteService.save(utente);
        credenziali.setUtente(utente);
        this.credenzialiService.save(credenziali);
        return "redirect:/login";
    }

    @Autowired private LibroService libroService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("ultimiArrivi", libroService.findUltimiArrivi());
        model.addAttribute("emozioni", EmozioneLibro.values());
        return "index";
    }
}