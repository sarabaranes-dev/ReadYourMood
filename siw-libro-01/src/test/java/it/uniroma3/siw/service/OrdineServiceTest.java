package it.uniroma3.siw.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import it.uniroma3.siw.model.Libro;
import it.uniroma3.siw.model.Ordine;
import it.uniroma3.siw.model.StatoLibro;
import it.uniroma3.siw.model.StatoOrdine;
import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.repository.OrdineRepository;

/** Unit tests for the order use cases (no database needed). */
@ExtendWith(MockitoExtension.class)
class OrdineServiceTest {

    @Mock
    private OrdineRepository ordineRepository;

    @InjectMocks
    private OrdineService ordineService;

    private Libro libro(double prezzo) {
        Libro libro = new Libro();
        libro.setPrezzo(prezzo);
        libro.setStato(StatoLibro.DISPONIBILE);
        return libro;
    }

    @Test
    void acquistaLibro_createsCompletedOrderAndMarksBookAsSold() {
        when(ordineRepository.save(any(Ordine.class))).thenAnswer(inv -> inv.getArgument(0));
        Utente acquirente = new Utente();
        Libro libro = libro(8.0);

        Ordine ordine = ordineService.acquistaLibro(libro, acquirente);

        assertEquals(StatoOrdine.COMPLETATO, ordine.getStato());
        assertEquals(8.0, ordine.getTotale());
        assertEquals(acquirente, ordine.getAcquirente());
        assertEquals(List.of(libro), ordine.getLibri());
        assertEquals(StatoLibro.VENDUTO, libro.getStato());
        verify(ordineRepository).save(ordine);
    }

    @Test
    void creaSet_sumsPricesAndMarksEveryBookAsSold() {
        when(ordineRepository.save(any(Ordine.class))).thenAnswer(inv -> inv.getArgument(0));
        List<Libro> libri = List.of(libro(5.0), libro(7.5), libro(10.0));

        Ordine ordine = ordineService.creaSet(libri, new Utente());

        assertEquals(22.5, ordine.getTotale());
        assertEquals(3, ordine.getLibri().size());
        libri.forEach(l -> assertEquals(StatoLibro.VENDUTO, l.getStato()));
    }
}
