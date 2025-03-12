package org.generationitaly.progetto.progetto.entity;

import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Utente {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 64)
    private String nome;

    @Column(length = 64)
    private String cognome;

    @Column(length = 64)
    private String numero;

    
    @ManyToMany(mappedBy = "utenti")
    private List<Programma> programmiPref;

    public Utente() {
        programmiPref = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public List<Programma> getProgrammiPref() {
        return programmiPref;
    }

    public void setProgrammiPref(List<Programma> programmiPref) {
        this.programmiPref = programmiPref;
    }

    @Override
    public String toString() {
        return "Utente [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", numero=" + numero
                + ", programmiPref=" + programmiPref + "]";
    }
}
