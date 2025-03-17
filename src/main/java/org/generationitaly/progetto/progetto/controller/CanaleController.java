package org.generationitaly.progetto.progetto.controller;

import java.util.List;

import org.generationitaly.progetto.progetto.entity.Canale;
import org.generationitaly.progetto.progetto.entity.Programma;
import org.generationitaly.progetto.progetto.service.CanaleService;
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
@RequestMapping("/api/canale")
public class CanaleController {

    @Autowired
    private CanaleService canaleService;

    @GetMapping("/canali")
    public ResponseEntity<?> getCanali(){
        try {
            List<Canale> canale = canaleService.findall();
            return ResponseEntity.ok(canale);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/canale/{id}")
    public ResponseEntity<?> getCanale(@PathVariable Long id){
        try {
            Canale canale = canaleService.findById(id);
            return ResponseEntity.ok(canale);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/CanaleProgrammi/{id}")
    public ResponseEntity<?> getProgrammiCanali(@PathVariable Long id) {
        try {
            List<Programma> programmiCanale = canaleService.trovaProgrammiCanale(id);
            return ResponseEntity.ok(programmiCanale);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/aggiungiCanale")
    public ResponseEntity<?> salvaCanale(@RequestBody Canale canale){
        try {
            if (canale.getNomeCanale()==null){
                canale.setNomeCanale("Nome canale inesistente");
            }
            canaleService.save(canale);
            return ResponseEntity.ok(canale);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/modificaCanale/{id}")
    public ResponseEntity<?> modificaCanale(@PathVariable Long id, @RequestBody Canale canaleM){
        try {
            Canale canale = canaleService.findById(id);
            if(canaleM.getNomeCanale().isEmpty()){
                canale.setNomeCanale("Nome canale inesistente");
            }else{
                canale.setNomeCanale(canaleM.getNomeCanale());
            }
            canaleService.save(canale);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/eliminaCanale/{id}")
    public ResponseEntity<?> deleteCanale(@PathVariable Long id) {
        try {
            canaleService.deleteCanale(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}