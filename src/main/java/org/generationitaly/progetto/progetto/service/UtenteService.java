package org.generationitaly.progetto.progetto.service;

import java.util.List;

import org.generationitaly.progetto.progetto.entity.Programma;
import org.generationitaly.progetto.progetto.entity.Utente;
import org.generationitaly.progetto.progetto.repo.UtenteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepo utenteRepo;

    public List<Utente> findall() {
        return utenteRepo.findAll();
    }

    public Utente findById(Long id) {
        return utenteRepo.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        utenteRepo.deleteById(id);
    }

    public void delete(Utente utente) {
        utenteRepo.delete(utente);
    }

    @Transactional
    public void deleteUtente(Long id) {

        Utente utente = utenteRepo.findById(id).orElse(null);

        // Rimuove il canale da tutti i programmi associati
        for (Programma programma : utente.getProgrammiPref()) {
            programma.getUtenti().remove(utente);
        }

        // Salva i programmi per aggiornare il DB
        utenteRepo.save(utente);

        // Ora puoi eliminare il canale
        utenteRepo.delete(utente);
    }

    public Utente save(Utente utente) {
        return utenteRepo.save(utente);
    }
}
