package it.uniroma3.siw.model;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;
    private String nome;
    private String cognome;

    @Enumerated(EnumType.STRING)
    private StatoUtente stato;

    // libri che l'utente vende
    @OneToMany(mappedBy = "venditore")
    private List<Libro> libriInVendita;

    // ordini che l'utente ha effettuato come acquirente
    @OneToMany(mappedBy = "acquirente")
    private List<Ordine> ordini;

    // recensioni ricevute come venditore
    @OneToMany(mappedBy = "venditore")
    private List<Recensione> recensioniRicevute;


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCognome() { return cognome; }
    public void setCognome(String cognome) { this.cognome = cognome; }
    public StatoUtente getStato() { return stato; }
    public void setStato(StatoUtente stato) { this.stato = stato; }
    public List<Libro> getLibriInVendita() { return libriInVendita; }
    public void setLibriInVendita(List<Libro> libriInVendita) { this.libriInVendita = libriInVendita; }
    public List<Ordine> getOrdini() { return ordini; }
    public void setOrdini(List<Ordine> ordini) { this.ordini = ordini; }
    public List<Recensione> getRecensioniRicevute() { return recensioniRicevute; }
    public void setRecensioniRicevute(List<Recensione> r) { this.recensioniRicevute = r; }

    @Override
    public int hashCode() { return Objects.hash(email); }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Utente other = (Utente) obj;
        return Objects.equals(email, other.email);
    }
}