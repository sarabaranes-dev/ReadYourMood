package it.uniroma3.siw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import it.uniroma3.siw.model.*;
import java.util.List;

public interface OrdineRepository extends JpaRepository<Ordine, Long> {
    List<Ordine> findByAcquirente(Utente acquirente);
    List<Ordine> findByAcquirenteAndStato(Utente acquirente, StatoOrdine stato);
}