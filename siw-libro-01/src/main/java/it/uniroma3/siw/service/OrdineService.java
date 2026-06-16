package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.uniroma3.siw.model.*;
import it.uniroma3.siw.repository.OrdineRepository;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrdineService {

    @Autowired
    private OrdineRepository ordineRepository;


    // CASO D'USO: acquisto singolo libro (Acquista Subito)
    @Transactional
    public Ordine acquistaLibro(Libro libro, Utente acquirente) {
        Ordine ordine = new Ordine();
        ordine.setAcquirente(acquirente);
        ordine.setDataOrdine(LocalDateTime.now());
        ordine.setStato(StatoOrdine.COMPLETATO);
        ordine.setTotale(libro.getPrezzo());
        ordine.setLibri(List.of(libro));

        // marca il libro come venduto
        libro.setStato(StatoLibro.VENDUTO);

        return this.ordineRepository.save(ordine);
    }

    // CASO D'USO: acquisto set di libri (Crea Set dallo stesso venditore)
    @Transactional
    public Ordine creaSet(List<Libro> libri, Utente acquirente) {
        double totale = libri.stream()
            .mapToDouble(Libro::getPrezzo)
            .sum();

        Ordine ordine = new Ordine();
        ordine.setAcquirente(acquirente);
        ordine.setDataOrdine(LocalDateTime.now());
        ordine.setStato(StatoOrdine.COMPLETATO);
        ordine.setTotale(totale);
        ordine.setLibri(libri);

        // marca tutti i libri come venduti
        for (Libro l : libri) {
            l.setStato(StatoLibro.VENDUTO);
        }

        return this.ordineRepository.save(ordine);
    }

    @Transactional(readOnly = true)
    public List<Ordine> findByAcquirente(Utente acquirente) {
        return this.ordineRepository.findByAcquirente(acquirente);
    }

    @Transactional(readOnly = true)
    public List<Ordine> findCompletatiByAcquirente(Utente acquirente) {
        return this.ordineRepository.findByAcquirenteAndStato(
            acquirente, StatoOrdine.COMPLETATO);
    }

    @Transactional(readOnly = true)
    public Ordine findById(Long id) {
        return this.ordineRepository.findById(id).get();
    }
}