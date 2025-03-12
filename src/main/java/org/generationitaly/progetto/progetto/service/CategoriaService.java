package org.generationitaly.progetto.progetto.service;

import java.util.List;


import org.generationitaly.progetto.progetto.entity.Categoria;

import org.generationitaly.progetto.progetto.repo.CategoriaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepo categoriaRepo;





    public List<Categoria> findall(){
        return categoriaRepo.findAll();
    }

    public Categoria findById(Long id){
        return categoriaRepo.findById(id).orElse(null);
    }

    public void deleteById(Long id){
        categoriaRepo.deleteById(id);
    }

    public void delete(Categoria categoria){
        categoriaRepo.delete(categoria);
    }

    public Categoria save(Categoria categoria) {
        return categoriaRepo.save(categoria);
    }
}
