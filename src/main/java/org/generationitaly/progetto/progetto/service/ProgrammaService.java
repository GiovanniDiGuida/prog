package org.generationitaly.progetto.progetto.service;

import java.util.List;

import org.generationitaly.progetto.progetto.entity.Programma;
import org.generationitaly.progetto.progetto.repo.ProgrammaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProgrammaService {
    @Autowired
    private ProgrammaRepo programmaRepo;

    public List<Programma> findall(){
        return programmaRepo.findAll();
    }

    public Programma findById(Long id){
        return programmaRepo.findById(id).orElse(null);
    }

    public void deleteById(Long id){
        programmaRepo.deleteById(id);
    }

    public void delete(Programma programma){
        programmaRepo.delete(programma);
    }

    public Programma save(Programma programma) {
        return programmaRepo.save(programma);
    }
}
