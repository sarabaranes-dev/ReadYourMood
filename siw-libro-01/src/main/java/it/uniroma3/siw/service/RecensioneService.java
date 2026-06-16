package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.uniroma3.siw.model.*;
import it.uniroma3.siw.repository.RecensioneRepository;
import java.time.LocalDate;
import java.util.List;

@Service
public class RecensioneService {

    @Autowired
    private RecensioneRepository recensioneRepository;

    // CASO D'USO: inserimento recensione (solo dopo ordine completato)
    @Transactional
    public boolean save(Recensione recensione, Utente acquirente, Ordine ordine) {
        // verifica che l'ordine appartenga all'acquirente e sia completato
        if (!ordine.getAcquirente().getId().equals(acquirente.getId())) return false;
        if (ordine.getStato() != StatoOrdine.COMPLETATO) return false;
        // verifica che non esista già una recensione per questo ordine
        if (this.recensioneRepository.existsByAcquirenteAndOrdine(acquirente, ordine)) return false;

        // il venditore è il venditore del primo libro dell'ordine
        Utente venditore = ordine.getLibri().get(0).getVenditore();

        recensione.setAcquirente(acquirente);
        recensione.setVenditore(venditore);
        recensione.setOrdine(ordine);
        recensione.setDataRecensione(LocalDate.now());
        this.recensioneRepository.save(recensione);
        return true;
    }

    @Transactional(readOnly = true)
    public List<Recensione> findByVenditore(Utente venditore) {
        return this.recensioneRepository.findByVenditore(venditore);
    }

    @Transactional(readOnly = true)
    public double getMediaVoti(Utente venditore) {
        List<Recensione> recensioni = this.recensioneRepository.findByVenditore(venditore);
        if (recensioni.isEmpty()) return 0.0;
        return recensioni.stream()
            .mapToInt(Recensione::getVoto)
            .average()
            .orElse(0.0);
    }

    @Transactional(readOnly = true)
    public List<Long> getOrdiniRecensitiIds(Utente acquirente) {
        return this.recensioneRepository.findOrdiniRecensitiIdsByAcquirente(acquirente);
    }
}