package org.generationitaly.progetto.progetto.controller;

import java.util.ArrayList;
import java.util.List;

import org.generationitaly.progetto.progetto.entity.Programma;
import org.generationitaly.progetto.progetto.entity.Utente;
import org.generationitaly.progetto.progetto.service.UtenteService;
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
@RequestMapping("/api/utente")
public class UtenteController {

    @Autowired
    private UtenteService utenteService;

    @GetMapping("/utenti")
    public ResponseEntity<?> getUtenti() {
        try {
            List<Utente> utenti = utenteService.findall();
            return ResponseEntity.ok(utenti);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/utente/{id}")
    public ResponseEntity<?> getCanale(@PathVariable Long id) {
        try {
            Utente utente = utenteService.findById(id);
            return ResponseEntity.ok(utente);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/aggiungiUtente")
    public ResponseEntity<?> salvaUtente(@RequestBody Utente utente) {
        try {
            if (!utente.getProgrammiPref().isEmpty()) {
                List<Programma> programmi = new ArrayList<>();
                for (int i = 0; i < utente.getProgrammiPref().size(); i++) {
                    Programma p = utente.getProgrammiPref().get(i);
                    programmi.add(p);
                }
                for (int i = 0; i < utente.getProgrammiPref().size(); i++) {
                    utente.getProgrammiPref().get(i).getUtenti().add(utente);
                }
            }
            utenteService.save(utente);
            return ResponseEntity.ok(utente);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/modificaUtente/{id}")
    public ResponseEntity<?> modificaUtente(@PathVariable Long id, @RequestBody Utente utenteM) {
        try {
            Utente utente = utenteService.findById(id);
            utente.setNome(utenteM.getNome());
            utente.setCognome(utenteM.getCognome());
            utente.setNumero(utenteM.getNumero());
            utente.setProgrammiPref(utenteM.getProgrammiPref());
            utenteService.save(utente);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/eliminaUtente/{id}")
    public ResponseEntity<?> deleteCategoria(@PathVariable Long id) {
        try {
            utenteService.deleteUtente(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
