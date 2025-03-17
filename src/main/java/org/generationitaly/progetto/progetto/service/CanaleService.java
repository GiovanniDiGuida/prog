package org.generationitaly.progetto.progetto.service;

import java.util.List;

import org.generationitaly.progetto.progetto.entity.Canale;
import org.generationitaly.progetto.progetto.entity.Programma;
import org.generationitaly.progetto.progetto.repo.CanaleRepo;
import org.generationitaly.progetto.progetto.repo.ProgrammaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class CanaleService {

    @Autowired
    private CanaleRepo canaleRepo;

    @Autowired
    private ProgrammaRepo programmaRepo;

    public List<Canale> findall() {
        return canaleRepo.findAll();
    }

    public Canale findById(Long id) {
        return canaleRepo.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        canaleRepo.deleteById(id);
    }

    public List<Programma> trovaProgrammiCanale(Long id) {
        return canaleRepo.findProgrammiByCanaleId(id);
    }

    @Transactional
    public void deleteCanale(Long id) {
        Canale canale = canaleRepo.findById(id).orElse(null);

        // Rimuove il canale da tutti i programmi associati
        for (Programma programma : canale.getProgrammi()) {
            programma.getCanali().remove(canale);
        }

        // Salva i programmi per aggiornare il DB
        programmaRepo.saveAll(canale.getProgrammi());

        // Ora puoi eliminare il canale
        canaleRepo.delete(canale);
    }

    @Transactional
    public Canale save(Canale canale) {
        return canaleRepo.save(canale);
    }
}
