package it.uniroma3.siw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.model.*;
import java.util.List;

public interface RecensioneRepository extends JpaRepository<Recensione, Long> {
    List<Recensione> findByVenditore(Utente venditore);
    boolean existsByAcquirenteAndOrdine(Utente acquirente, Ordine ordine);

    @Query("SELECT r.ordine.id FROM Recensione r WHERE r.acquirente = :acquirente")
    List<Long> findOrdiniRecensitiIdsByAcquirente(@Param("acquirente") Utente acquirente);
}
