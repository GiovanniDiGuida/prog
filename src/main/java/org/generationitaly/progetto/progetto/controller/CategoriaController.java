package org.generationitaly.progetto.progetto.controller;

import java.util.List;

import org.generationitaly.progetto.progetto.entity.Categoria;
import org.generationitaly.progetto.progetto.entity.Programma;
import org.generationitaly.progetto.progetto.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/categoria")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    @GetMapping("/categorie")
    public ResponseEntity<?> getCategorie() {
        try {
            List<Categoria> categorie = categoriaService.findall();
            return ResponseEntity.ok(categorie);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/categoria/{id}")
    public ResponseEntity<?> getCategoriaSingola(@PathVariable Long id) {
        try {
            Categoria categoria = categoriaService.findById(id);
            return ResponseEntity.ok(categoria);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/programmiCategoria/{id}")
    public ResponseEntity<?> getProgrammiCategoria(@PathVariable Long id) {
        try {
            List<Programma> programmiCategoria = categoriaService.programmiDiCategoria(id);
            return ResponseEntity.ok(programmiCategoria);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/aggiungiCategoria")
    public ResponseEntity<?> aggiungiCategoria(@RequestBody Categoria categoria) {
        try {
            //controllo se il nome della categoria è vuoto
            if (categoria.getNomeCategoria() == null) {
                categoria.setNomeCategoria("Nome categoria inesistente");
            }
            categoriaService.save(categoria);
            return ResponseEntity.ok(categoria);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/modificaCategoria/{id}")
    public ResponseEntity<?> modificaCategoria(@PathVariable Long id, @RequestBody Categoria categoriaM) {
        try {
            Categoria categoria = categoriaService.findById(id);
            //controllo se il nome della categoria è vuoto
            if (categoriaM.getNomeCategoria().isEmpty()) {
                categoria.setNomeCategoria("Nome categoria inesistente");
            } else {
                categoria.setNomeCategoria(categoriaM.getNomeCategoria());
            }

            categoriaService.save(categoria);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/eliminaCategoria/{id}")
    public ResponseEntity<?> deleteCategoria(@PathVariable Long id) {
        try {
            categoriaService.deleteCategoria(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
