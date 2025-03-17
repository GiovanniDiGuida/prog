package org.generationitaly.progetto.progetto.repo;

import java.util.List;

import org.generationitaly.progetto.progetto.entity.Canale;
import org.generationitaly.progetto.progetto.entity.Programma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CanaleRepo extends JpaRepository<Canale, Long> {
    public List<Canale> findByNomeCanaleLike(String nomeCanale);

    @Query("SELECT p FROM Canale c JOIN c.programmi p WHERE c.id = :canaleId ORDER BY p.orario")
    List<Programma> findProgrammiByCanaleId(@Param("canaleId") Long canaleId);
}
