package it.uniroma3.siw.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String titolo;
    private String autore;
    private Integer anno;

    @Column(length = 2000)
    private String trama;

    private Integer valutazione;  // 1-5 stelle

    @Enumerated(EnumType.STRING)
    private EmozioneLibro emozione;

    @Enumerated(EnumType.STRING)
    private CondizioneLibro condizione;

    private Double prezzo;
    private LocalDate dataInserimento;

    @Enumerated(EnumType.STRING)
    private StatoLibro stato;

    private String foto;

    @JsonIgnore
    @ManyToOne
    // molti libri appartengono a un solo venditore
    private Utente venditore;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitolo() { return titolo; }
    public void setTitolo(String titolo) { this.titolo = titolo; }
    public String getAutore() { return autore; }
    public void setAutore(String autore) { this.autore = autore; }
    public Integer getAnno() { return anno; }
    public void setAnno(Integer anno) { this.anno = anno; }
    public String getTrama() { return trama; }
    public void setTrama(String trama) { this.trama = trama; }
    public Integer getValutazione() { return valutazione; }
    public void setValutazione(Integer valutazione) { this.valutazione = valutazione; }
    public EmozioneLibro getEmozione() { return emozione; }
    public void setEmozione(EmozioneLibro emozione) { this.emozione = emozione; }
    public CondizioneLibro getCondizione() { return condizione; }
    public void setCondizione(CondizioneLibro condizione) { this.condizione = condizione; }
    public Double getPrezzo() { return prezzo; }
    public void setPrezzo(Double prezzo) { this.prezzo = prezzo; }
    public LocalDate getDataInserimento() { return dataInserimento; }
    public void setDataInserimento(LocalDate dataInserimento) { this.dataInserimento = dataInserimento; }
    public StatoLibro getStato() { return stato; }
    public void setStato(StatoLibro stato) { this.stato = stato; }
    public String getFoto() { return foto; }
    public void setFoto(String foto) { this.foto = foto; }
    public Utente getVenditore() { return venditore; }
    public void setVenditore(Utente venditore) { this.venditore = venditore; }

    @Override
    public int hashCode() { return Objects.hash(titolo, autore, anno); }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Libro other = (Libro) obj;
        return Objects.equals(titolo, other.titolo)
            && Objects.equals(autore, other.autore)
            && Objects.equals(anno, other.anno);
    }
}