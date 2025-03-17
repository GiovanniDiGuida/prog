package org.generationitaly.progetto.progetto.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Canale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    @Column(length=64)
    private String nomeCanale;

    @JsonBackReference
    @ManyToMany(mappedBy ="canali")
    private List<Programma> programmi;

    public Canale() {
        programmi= new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCanale() {
        return nomeCanale;
    }

    public void setNomeCanale(String nomeCanale) {
        this.nomeCanale = nomeCanale;
    }

    public List<Programma> getProgrammi() {
        return programmi;
    }

    public void setProgrammi(List<Programma> programmi) {
        this.programmi = programmi;
    }

    @Override
    public String toString() {
        return "Canale [id=" + id + ", nomeCanale=" + nomeCanale + ", programmi=" + programmi + "]";
    }
}
