package it.uniroma3.siw.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Recensione {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer voto;  // 1-5 stelle

    @Column(length = 1000)
    private String commento;

    private LocalDate dataRecensione;

    @ManyToOne
    // chi ha scritto la recensione
    private Utente acquirente;

    @ManyToOne
    // il venditore a cui si riferisce
    private Utente venditore;

    @ManyToOne
    // l'ordine completato su cui si basa
    private Ordine ordine;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Integer getVoto() { return voto; }
    public void setVoto(Integer voto) { this.voto = voto; }
    public String getCommento() { return commento; }
    public void setCommento(String commento) { this.commento = commento; }
    public LocalDate getDataRecensione() { return dataRecensione; }
    public void setDataRecensione(LocalDate dataRecensione) { this.dataRecensione = dataRecensione; }
    public Utente getAcquirente() { return acquirente; }
    public void setAcquirente(Utente acquirente) { this.acquirente = acquirente; }
    public Utente getVenditore() { return venditore; }
    public void setVenditore(Utente venditore) { this.venditore = venditore; }
    public Ordine getOrdine() { return ordine; }
    public void setOrdine(Ordine ordine) { this.ordine = ordine; }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Recensione other = (Recensione) obj;
        return Objects.equals(id, other.id);
    }
}