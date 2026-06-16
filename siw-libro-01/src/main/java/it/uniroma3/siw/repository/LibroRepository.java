package it.uniroma3.siw.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import it.uniroma3.siw.model.*;
import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    // ricerca per titolo
    Page<Libro> findByTitoloContainingIgnoreCaseAndStato(
        String titolo, StatoLibro stato, Pageable pageable);

    // ricerca per autore
    Page<Libro> findByAutoreContainingIgnoreCaseAndStato(
        String autore, StatoLibro stato, Pageable pageable);

    // ricerca per emozione
    Page<Libro> findByEmozioneAndStato(
        EmozioneLibro emozione, StatoLibro stato, Pageable pageable);

    // tutti i libri disponibili con paginazione
    Page<Libro> findByStato(StatoLibro stato, Pageable pageable);

    // libri di un venditore
    List<Libro> findByVenditore(Utente venditore);

    // libri di un venditore disponibili
    List<Libro> findByVenditoreAndStato(Utente venditore, StatoLibro stato);
    
    List<Libro> findTop10ByStatoOrderByDataInserimentoDesc(StatoLibro stato);
    
    Page<Libro> findByAnnoAndStato(Integer anno, StatoLibro stato, Pageable pageable);
    
    Page<Libro> findByValutazioneAndStato(Integer valutazione, StatoLibro stato, Pageable pageable);
}