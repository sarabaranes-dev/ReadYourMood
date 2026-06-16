package it.uniroma3.siw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import it.uniroma3.siw.model.Credenziali;
import java.util.Optional;

public interface CredenzialiRepository extends JpaRepository<Credenziali, Long> {
    Optional<Credenziali> findByUsername(String username);
}