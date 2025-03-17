package org.generationitaly.progetto.progetto.repo;

import java.util.List;

import org.generationitaly.progetto.progetto.entity.Categoria;
import org.generationitaly.progetto.progetto.entity.Programma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepo extends JpaRepository<Categoria, Long> {

    public List<Categoria> findByNomeCategoriaLike(String nomeCategoria);

    // public List<Categoria> findAllById(List<Long> id);
    @Query("SELECT p FROM Categoria c JOIN c.programmi p WHERE c.id = :categoriaId ORDER BY p.orario")
    public List<Programma> findProgrammiByCategoriaId(@Param("categoriaId") Long categoriaId);
}
