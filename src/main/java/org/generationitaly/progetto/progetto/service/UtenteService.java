package org.generationitaly.progetto.progetto.service;

import java.util.List;


import org.generationitaly.progetto.progetto.entity.Utente;

import org.generationitaly.progetto.progetto.repo.UtenteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtenteService {
    
    @Autowired
    private UtenteRepo utenteRepo;

    public List<Utente> findall(){
        return utenteRepo.findAll();
    }

    public Utente findById(Long id){
        return utenteRepo.findById(id).orElse(null);
    }

    public void deleteById(Long id){
        utenteRepo.deleteById(id);
    }

    public void delete(Utente utente){
        utenteRepo.delete(utente);
    }

    public Utente save(Utente utente) {
        return utenteRepo.save(utente);
    }
}
