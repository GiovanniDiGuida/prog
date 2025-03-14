package org.generationitaly.progetto.progetto.controller;

import java.util.ArrayList;
import java.util.List;

import org.generationitaly.progetto.progetto.entity.Programma;
import org.generationitaly.progetto.progetto.entity.Utente;
import org.generationitaly.progetto.progetto.repo.ProgrammaRepo;
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

    @Autowired
    private ProgrammaRepo programmaRepo;


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
    public ResponseEntity<?> getUente(@PathVariable Long id) {
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
            if (utente.getProgrammiPref() != null && !utente.getProgrammiPref().isEmpty()) {
                List<Programma> programmi = new ArrayList<>();

                // Scorriamo la lista con un for classico
                for (int i = 0; i < utente.getProgrammiPref().size(); i++) {
                    Programma p = utente.getProgrammiPref().get(i);
                    Programma programmaEsistente = programmaRepo.findById(p.getId()).orElse(null);

                    if (programmaEsistente != null) {
                        programmi.add(programmaEsistente);
                        programmaEsistente.getUtenti().add(utente); // Associazione bidirezionale
                    }
                }

                utente.setProgrammiPref(programmi); // Aggiorna la lista di programmi dell'utente
            }

            // Salva l'utente con i programmi aggiornati
            Utente savedUser = utenteService.save(utente);
            return ResponseEntity.ok(savedUser);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/modificaUtente/{id}")
    public ResponseEntity<?> modificaUtente(@PathVariable Long id, @RequestBody Utente utenteM) {
        try {
            Utente utente = utenteService.findById(id);
            if (utente == null) {
                return new ResponseEntity<>("Utente non trovato", HttpStatus.NOT_FOUND);
            }
            if (utenteM.getNome()!=null){
                utente.setNome(utenteM.getNome());
            }else{
                utente.setNome(" ");
            }
            utente.setCognome(utenteM.getCognome());
            utente.setNumero(utenteM.getNumero());

            // Se l'utente ha aggiornato i programmi preferiti
            if (utenteM.getProgrammiPref() != null) {
                List<Programma> programmiAggiornati = new ArrayList<>();

                // Recuperiamo i programmi dal database usando un for classico
                for (int i = 0; i < utenteM.getProgrammiPref().size(); i++) {
                    Programma p = utenteM.getProgrammiPref().get(i);
                    Programma programmaEsistente = programmaRepo.findById(p.getId()).orElse(null);

                    if (programmaEsistente != null) {
                        programmiAggiornati.add(programmaEsistente);
                        programmaEsistente.getUtenti().add(utente); // Associazione bidirezionale
                    }
                }

                // Rimuoviamo le vecchie associazioni dai programmi precedenti
                for (Programma p : utente.getProgrammiPref()) {
                    p.getUtenti().remove(utente);
                }

                // Impostiamo la nuova lista aggiornata
                utente.setProgrammiPref(programmiAggiornati);
            }

            // Salviamo l'utente aggiornato
            utenteService.save(utente);
            return ResponseEntity.ok(utente);
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
