package it.uniroma3.siw.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class Credenziali {

    public static final String USER_ROLE = "USER";
    public static final String ADMIN_ROLE = "ADMIN";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;
    private String ruolo;

    @OneToOne
    private Utente utente;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRuolo() { return ruolo; }
    public void setRuolo(String ruolo) { this.ruolo = ruolo; }
    public Utente getUtente() { return utente; }
    public void setUtente(Utente utente) { this.utente = utente; }

    @Override
    public int hashCode() { return Objects.hash(username); }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Credenziali other = (Credenziali) obj;
        return Objects.equals(username, other.username);
    }
}