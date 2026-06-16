package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.uniroma3.siw.model.*;
import it.uniroma3.siw.repository.UtenteRepository;
import java.util.List;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    // CASO D'USO: registrazione — salva nuovo utente
    @Transactional
    public void save(Utente utente) {
        utente.setStato(StatoUtente.ATTIVO);
        this.utenteRepository.save(utente);
    }

    @Transactional(readOnly = true)
    public Utente findById(Long id) {
        return this.utenteRepository.findById(id).get();
    }

    @Transactional(readOnly = true)
    public Utente findByEmail(String email) {
        return this.utenteRepository.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public List<Utente> findAll() {
        return this.utenteRepository.findAll();
    }

    // CASO D'USO: blocco/sblocco utente (admin)
    @Transactional
    public void toggleStato(Long id) {
        Utente utente = this.utenteRepository.findById(id).get();
        if (utente.getStato() == StatoUtente.ATTIVO) {
            utente.setStato(StatoUtente.BLOCCATO);
        } else {
            utente.setStato(StatoUtente.ATTIVO);
        }
       
    }
}