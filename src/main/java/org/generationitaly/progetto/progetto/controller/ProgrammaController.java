package org.generationitaly.progetto.progetto.controller;

import java.util.List;


import org.generationitaly.progetto.progetto.entity.Programma;
import org.generationitaly.progetto.progetto.service.ProgrammaService;
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
@RequestMapping("/api/programma")
public class ProgrammaController {

    @Autowired
    private ProgrammaService programmaService;
 
    @GetMapping("/programmi")
    public ResponseEntity<?> getProgrammi() {
        try {
            List<Programma> programmi = programmaService.findall();
            return ResponseEntity.ok(programmi);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/programma/{id}")
    public ResponseEntity<?> getSingoloProgramma(@PathVariable Long id){
        try {
            Programma programma = programmaService.findById(id);
            return ResponseEntity.ok(programma);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/aggiungiProgramma")
    public ResponseEntity<?> salvaProgramma(@RequestBody Programma programma){
        try {
            programmaService.save(programma);
            return ResponseEntity.ok(programma);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/modificaProgramma/{id}")
    public ResponseEntity<?> modificaCategoria(@PathVariable Long id, @RequestBody Programma programmaM){
        try {
            Programma programma = programmaService.findById(id);
            programma.setTitolo(programmaM.getTitolo());
            programma.setDescrizione(programmaM.getDescrizione());
            programma.setOrario(programma.getOrario());
            programma.setCategorie(programmaM.getCategorie());
            programma.setUtenti(programmaM.getUtenti());
            programma.setCanali(programmaM.getCanali());
            
            programmaService.save(programma);
            return new ResponseEntity<>(HttpStatus.OK);
        
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/eliminaProgramma/{id}")
    public ResponseEntity<?> deleteCanale(@PathVariable Long id) {
        try {
            programmaService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
