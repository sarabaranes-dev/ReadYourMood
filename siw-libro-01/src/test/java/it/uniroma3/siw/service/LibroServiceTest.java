package it.uniroma3.siw.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import it.uniroma3.siw.model.EmozioneLibro;
import it.uniroma3.siw.model.Libro;
import it.uniroma3.siw.model.StatoLibro;
import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.repository.LibroRepository;

/** Unit tests for the book use cases (no database needed). */
@ExtendWith(MockitoExtension.class)
class LibroServiceTest {

    @Mock
    private LibroRepository libroRepository;

    @InjectMocks
    private LibroService libroService;

    private Utente utente(long id) {
        Utente utente = new Utente();
        utente.setId(id);
        return utente;
    }

    private Libro libroInVendita(long id, Utente venditore, StatoLibro stato) {
        Libro libro = new Libro();
        libro.setId(id);
        libro.setTitolo("Old title");
        libro.setVenditore(venditore);
        libro.setStato(stato);
        return libro;
    }

    @Test
    void findByEmozione_searchesOnlyAvailableBooks() {
        when(libroRepository.findByEmozioneAndStato(EmozioneLibro.FELICE, StatoLibro.DISPONIBILE, PageRequest.of(0, 6)))
            .thenReturn(Page.empty());

        libroService.findByEmozione(EmozioneLibro.FELICE, 0);

        verify(libroRepository).findByEmozioneAndStato(EmozioneLibro.FELICE, StatoLibro.DISPONIBILE, PageRequest.of(0, 6));
    }

    @Test
    void save_setsSellerDateAndAvailableStatus() {
        Utente venditore = utente(1L);
        Libro libro = new Libro();

        libroService.save(libro, venditore);

        assertEquals(venditore, libro.getVenditore());
        assertEquals(StatoLibro.DISPONIBILE, libro.getStato());
        assertNotNull(libro.getDataInserimento());
        verify(libroRepository).save(libro);
    }

    @Test
    void update_byOwner_changesFieldsAndSaves() {
        Utente venditore = utente(1L);
        Libro esistente = libroInVendita(10L, venditore, StatoLibro.DISPONIBILE);
        when(libroRepository.findById(10L)).thenReturn(Optional.of(esistente));
        Libro modifiche = new Libro();
        modifiche.setId(10L);
        modifiche.setTitolo("New title");

        boolean ok = libroService.update(modifiche, venditore);

        assertTrue(ok);
        assertEquals("New title", esistente.getTitolo());
        verify(libroRepository).save(esistente);
    }

    @Test
    void update_byAnotherUser_isRejected() {
        Libro esistente = libroInVendita(10L, utente(1L), StatoLibro.DISPONIBILE);
        when(libroRepository.findById(10L)).thenReturn(Optional.of(esistente));
        Libro modifiche = new Libro();
        modifiche.setId(10L);
        modifiche.setTitolo("New title");

        boolean ok = libroService.update(modifiche, utente(2L));

        assertFalse(ok);
        assertEquals("Old title", esistente.getTitolo());
        verify(libroRepository, never()).save(any());
    }

    @Test
    void update_ofSoldBook_isRejected() {
        Utente venditore = utente(1L);
        Libro esistente = libroInVendita(10L, venditore, StatoLibro.VENDUTO);
        when(libroRepository.findById(10L)).thenReturn(Optional.of(esistente));
        Libro modifiche = new Libro();
        modifiche.setId(10L);

        assertFalse(libroService.update(modifiche, venditore));
        verify(libroRepository, never()).save(any());
    }
}
