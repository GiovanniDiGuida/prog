package org.generationitaly.progetto.progetto.service;

import java.util.List;

import org.generationitaly.progetto.progetto.entity.Categoria;
import org.generationitaly.progetto.progetto.entity.Programma;
import org.generationitaly.progetto.progetto.repo.CategoriaRepo;
import org.generationitaly.progetto.progetto.repo.ProgrammaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepo categoriaRepo;

    @Autowired
    private ProgrammaRepo programmaRepo;

    public List<Categoria> findall() {
        return categoriaRepo.findAll();
    }

    public Categoria findById(Long id) {
        return categoriaRepo.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        categoriaRepo.deleteById(id);
    }

    public List<Programma> programmiDiCategoria(Long id){
        return categoriaRepo.findProgrammiByCategoriaId(id);
    }

    @Transactional
    public void deleteCategoria(Long id) {
        Categoria categoria = categoriaRepo.findById(id).orElse(null);

        // Rimuove il canale da tutti i programmi associati
        for (Programma programma : categoria.getProgrammi()) {
            programma.getCategorie().remove(categoria);
        }

        // Salva i programmi per aggiornare il DB
        programmaRepo.saveAll(categoria.getProgrammi());

        // Ora puoi eliminare il canale
        categoriaRepo.delete(categoria);
    }

    @Transactional
    public void deleteCanale(Long id) {
        Categoria categoria = categoriaRepo.findById(id).orElse(null);

        // Rimuove il canale da tutti i programmi associati
        for (Programma programma : categoria.getProgrammi()) {
            programma.getCategorie().remove(categoria);
        }

        // Salva i programmi per aggiornare il DB
        programmaRepo.saveAll(categoria.getProgrammi());

        // Ora puoi eliminare il canale
        categoriaRepo.delete(categoria);
    }

    public Categoria save(Categoria categoria) {
        return categoriaRepo.save(categoria);
    }
}
