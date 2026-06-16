package it.uniroma3.siw.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
public class Ordine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime dataOrdine;
    private Double totale;

    @Enumerated(EnumType.STRING)
    private StatoOrdine stato;

    @ManyToOne
    // molti ordini appartengono a un acquirente
    private Utente acquirente;

    @ManyToMany
    // un ordine contiene più libri (dallo stesso venditore)
    private List<Libro> libri;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDateTime getDataOrdine() { return dataOrdine; }
    public void setDataOrdine(LocalDateTime dataOrdine) { this.dataOrdine = dataOrdine; }
    public Double getTotale() { return totale; }
    public void setTotale(Double totale) { this.totale = totale; }
    public StatoOrdine getStato() { return stato; }
    public void setStato(StatoOrdine stato) { this.stato = stato; }
    public Utente getAcquirente() { return acquirente; }
    public void setAcquirente(Utente acquirente) { this.acquirente = acquirente; }
    public List<Libro> getLibri() { return libri; }
    public void setLibri(List<Libro> libri) { this.libri = libri; }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Ordine other = (Ordine) obj;
        return Objects.equals(id, other.id);
    }
}