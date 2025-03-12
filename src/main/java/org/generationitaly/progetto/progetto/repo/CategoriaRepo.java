package org.generationitaly.progetto.progetto.repo;

import java.util.List;

import org.generationitaly.progetto.progetto.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepo extends JpaRepository<Categoria, Long>{
    public List<Categoria> findByNomeCategoriaLike(String nomeCategoria);
    // public List<Categoria> findAllById(List<Long> id);
}

