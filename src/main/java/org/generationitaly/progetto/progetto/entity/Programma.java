package org.generationitaly.progetto.progetto.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Programma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 64)
    private String titolo;

    @Column(columnDefinition = "TEXT")
    private String descrizione;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime orario;

    @ManyToMany
    private List<Canale> canali;

    @JsonBackReference("lista_utenti")
    @ManyToMany
    private List<Utente> utenti;

    @ManyToMany
    private List<Categoria> categorie;

    public Programma() {
        canali = new ArrayList<>();
        categorie = new ArrayList<>();
        utenti = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public LocalDateTime getOrario() {
        return orario;
    }

    public void setOrario(LocalDateTime orario) {
        this.orario = orario;
    }

    public List<Canale> getCanali() {
        return canali;
    }

    public void setCanali(List<Canale> canali) {
        this.canali = canali;
    }

    public List<Utente> getUtenti() {
        return utenti;
    }

    public void setUtenti(List<Utente> utenti) {
        this.utenti = utenti;
    }

    public List<Categoria> getCategorie() {
        return categorie;
    }

    public void setCategorie(List<Categoria> categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "Programma [id=" + id + ", titolo=" + titolo + ", descrizione=" + descrizione + ", orario=" + orario
                + ", canali=" + canali + ", utenti=" + utenti + ", categorie=" + categorie + "]";
    }
}
