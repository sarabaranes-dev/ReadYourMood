package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.uniroma3.siw.model.Credenziali;
import it.uniroma3.siw.repository.CredenzialiRepository;
import java.util.Optional;

@Service
public class CredenzialiService {

    @Autowired
    private CredenzialiRepository credenzialiRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public Credenziali findByUsername(String username) {
        Optional<Credenziali> result = this.credenzialiRepository.findByUsername(username);
        return result.orElse(null);
    }

    @Transactional
    public Credenziali save(Credenziali credenziali) {
        credenziali.setRuolo(Credenziali.USER_ROLE);
        credenziali.setPassword(this.passwordEncoder.encode(credenziali.getPassword()));
        return this.credenzialiRepository.save(credenziali);
    }
}