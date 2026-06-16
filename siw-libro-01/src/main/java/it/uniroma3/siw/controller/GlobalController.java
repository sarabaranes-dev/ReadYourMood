package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import it.uniroma3.siw.model.Credenziali;
import it.uniroma3.siw.service.CredenzialiService;

@ControllerAdvice
public class GlobalController {

    @Autowired
    private CredenzialiService credenzialiService;

    @ModelAttribute("userDetails")
    public UserDetails getUserDetails() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            return (UserDetails) auth.getPrincipal();
        }
        return null;
    }

    @ModelAttribute("utenteLoggato")
    public Credenziali getCredenzialiLoggato() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails ud = (UserDetails) auth.getPrincipal();
            return credenzialiService.findByUsername(ud.getUsername());
        }
        return null;
    }
}