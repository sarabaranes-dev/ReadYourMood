package it.uniroma3.siw.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.*;
import it.uniroma3.siw.service.*;

@Controller
@RequestMapping("/miei-libri")
public class LibroUtenteController {

    @Autowired private LibroService libroService;
    @Autowired private CredenzialiService credenzialiService;
    @Autowired private FileStorageService fileStorageService;

    private Utente getUtenteLoggato() {
        UserDetails ud = (UserDetails) SecurityContextHolder
            .getContext().getAuthentication().getPrincipal();
        return credenzialiService.findByUsername(ud.getUsername()).getUtente();
    }

    // CASO D'USO: form inserimento libro
    @GetMapping("/nuovo")
    public String showFormNuovo(Model model) {
        model.addAttribute("libro", new Libro());
        model.addAttribute("emozioni", EmozioneLibro.values());
        model.addAttribute("condizioni", CondizioneLibro.values());
        return "libri/form";
    }

    // CASO D'USO: salva nuovo libro
    @PostMapping("/save")
    public String save(Libro libro, @RequestParam(value = "file", required = false) MultipartFile file) {
        Utente utente = getUtenteLoggato();
        if (file != null && !file.isEmpty()) {
            try {
                libro.setFoto(fileStorageService.salva(file));
            } catch (IOException e) {
                System.err.println("Errore upload: " + e.getMessage());
            }
        }
        libroService.save(libro, utente);
        return "redirect:/area-personale";
    }

    // CASO D'USO: form modifica libro
    @GetMapping("/{id}/edit")
    public String showFormModifica(@PathVariable Long id, Model model) {
        Libro libro = libroService.findById(id);
        Utente utente = getUtenteLoggato();
        // solo il proprietario può modificare
        if (!libro.getVenditore().getId().equals(utente.getId()) || libro.getStato() == StatoLibro.VENDUTO) {
            return "redirect:/area-personale";
        }
        model.addAttribute("libro", libro);
        model.addAttribute("emozioni", EmozioneLibro.values());
        model.addAttribute("condizioni", CondizioneLibro.values());
        return "libri/form";
    }

    // CASO D'USO: salva modifiche libro
    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id, Libro libro,
                        @RequestParam(value = "file", required = false) MultipartFile file) {
        libro.setId(id);
        Libro esistente = libroService.findById(id);
        
        if (file != null && !file.isEmpty()) {
            try {
                if (esistente.getFoto() != null) fileStorageService.elimina(esistente.getFoto());
                libro.setFoto(fileStorageService.salva(file));
            } catch (IOException e) {
                libro.setFoto(esistente.getFoto());
            }
        } else {
            libro.setFoto(esistente.getFoto());
        }
        
        //recupero utente
        Utente utente = getUtenteLoggato();
        libroService.update(libro, utente);
        
        return "redirect:/area-personale";
    }

    // CASO D'USO: eliminazione proprio libro
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        Libro libro = libroService.findById(id);
        Utente utente = getUtenteLoggato();
        if (libro.getVenditore().getId().equals(utente.getId())) {
            libroService.deleteById(id);
        }
        return "redirect:/area-personale";
    }
}