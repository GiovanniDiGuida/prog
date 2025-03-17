package org.generationitaly.progetto.progetto.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 64)
    private String nomeCategoria;

    @JsonBackReference("lista_categoria")
    @ManyToMany(mappedBy = "categorie", cascade = CascadeType.ALL)
    private List<Programma> programmi;

    public Categoria() {
        programmi = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public List<Programma> getProgrammi() {
        return programmi;
    }

    public void setProgrammi(List<Programma> programmi) {
        this.programmi = programmi;
    }

    @Override
    public String toString() {
        return "Categoria [id=" + id + ", nomeCategoria=" + nomeCategoria + ", programmi=" + programmi + "]";
    }
}
