package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.uniroma3.siw.model.*;
import it.uniroma3.siw.repository.LibroRepository;
import java.time.LocalDate;
import java.util.List;

@Service
public class LibroService {

    private static final int PAGE_SIZE = 6;

    @Autowired
    private LibroRepository libroRepository;

    // CASO D'USO: elenco libri disponibili (pubblico)
    @Transactional(readOnly = true)
    public Page<Libro> findDisponibili(int page) {
        return this.libroRepository.findByStato(
            StatoLibro.DISPONIBILE, PageRequest.of(page, PAGE_SIZE));
    }

    // CASO D'USO: ricerca per titolo (pubblico)
    @Transactional(readOnly = true)
    public Page<Libro> findByTitolo(String titolo, int page) {
        return this.libroRepository.findByTitoloContainingIgnoreCaseAndStato(
            titolo, StatoLibro.DISPONIBILE, PageRequest.of(page, PAGE_SIZE));
    }

    // CASO D'USO: ricerca per autore (pubblico)
    @Transactional(readOnly = true)
    public Page<Libro> findByAutore(String autore, int page) {
        return this.libroRepository.findByAutoreContainingIgnoreCaseAndStato(
            autore, StatoLibro.DISPONIBILE, PageRequest.of(page, PAGE_SIZE));
    }

    // CASO D'USO: ricerca per emozione (pubblico)
    @Transactional(readOnly = true)
    public Page<Libro> findByEmozione(EmozioneLibro emozione, int page) {
        return this.libroRepository.findByEmozioneAndStato(
            emozione, StatoLibro.DISPONIBILE, PageRequest.of(page, PAGE_SIZE));
    }

    @Transactional(readOnly = true)
    public Libro findById(Long id) {
        return this.libroRepository.findById(id).get();
    }

    // CASO D'USO: inserimento libro (utente registrato)
    @Transactional
    public void save(Libro libro, Utente venditore) {
        libro.setVenditore(venditore);
        libro.setDataInserimento(LocalDate.now());
        libro.setStato(StatoLibro.DISPONIBILE);
        this.libroRepository.save(libro);
    }

    // CASO D'USO: modifica libro (utente registrato - solo proprio)
    @Transactional
    public boolean update(Libro libro, Utente utenteLoggato) {
        Libro esistente = this.libroRepository.findById(libro.getId()).orElse(null);
        
        if (esistente == null) {
            return false;
        }
        
        if (!esistente.getVenditore().getId().equals(utenteLoggato.getId())) {
            return false;
        }

        if (esistente.getStato() == StatoLibro.VENDUTO) {
            return false; 
        }
        
        //aggiorno campi modificabili
        esistente.setTitolo(libro.getTitolo());
        esistente.setAutore(libro.getAutore());
        esistente.setAnno(libro.getAnno());
        esistente.setTrama(libro.getTrama());
        esistente.setValutazione(libro.getValutazione());
        esistente.setEmozione(libro.getEmozione());
        esistente.setCondizione(libro.getCondizione());
        esistente.setPrezzo(libro.getPrezzo());
        esistente.setFoto(libro.getFoto()); 
        
        
        this.libroRepository.save(esistente);
        return true;
    }

    // CASO D'USO: eliminazione libro (utente registrato - solo proprio; admin tutti)
    @Transactional
    public void deleteById(Long id) {
        this.libroRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Libro> findByVenditore(Utente venditore) {
        return this.libroRepository.findByVenditore(venditore);
    }

    @Transactional(readOnly = true)
    public List<Libro> findDisponibiliByVenditore(Utente venditore) {
        return this.libroRepository.findByVenditoreAndStato(venditore, StatoLibro.DISPONIBILE);
    }
    
    
    @Transactional(readOnly = true)
    public List<Libro> findUltimiArrivi() {
        return this.libroRepository
            .findTop10ByStatoOrderByDataInserimentoDesc(StatoLibro.DISPONIBILE);
    }

    @Transactional(readOnly = true)
    public Page<Libro> findByAnno(Integer anno, int page) {
        return this.libroRepository.findByAnnoAndStato(
            anno, StatoLibro.DISPONIBILE, PageRequest.of(page, PAGE_SIZE));
    }

    @Transactional(readOnly = true)
    public Page<Libro> findByValutazione(Integer valutazione, int page) {
        return this.libroRepository.findByValutazioneAndStato(
            valutazione, StatoLibro.DISPONIBILE, PageRequest.of(page, PAGE_SIZE));
    }
    
    @Transactional(readOnly = true)
    public List<Libro> findAll() {
        return this.libroRepository.findAll();
    }
}