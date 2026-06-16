package it.uniroma3.siw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.model.StatoUtente;
import java.util.List;

public interface UtenteRepository extends JpaRepository<Utente, Long> {
    Utente findByEmail(String email);
    List<Utente> findByStato(StatoUtente stato);
}