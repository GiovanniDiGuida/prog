package org.generationitaly.progetto.progetto.repo;

import java.util.List;

import org.generationitaly.progetto.progetto.entity.Programma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgrammaRepo extends JpaRepository<Programma, Long>{
    
    @Query("SELECT p FROM Programma p JOIN p.categorie c WHERE c.nomeCategoria = :nomeCategoria")
    List<Programma> findByCategoriaNome(@Param("nomeCategoria") String nomeCategoria);

    
    @Query("SELECT p FROM Programma p JOIN p.canali c WHERE c.nomeCanale like  :nomeCanale")
    List<Programma> findByCanaleNome(@Param("nomeCanale") String nomeCanale);
}
