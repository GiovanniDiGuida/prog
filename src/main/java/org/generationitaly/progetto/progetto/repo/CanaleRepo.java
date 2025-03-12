package org.generationitaly.progetto.progetto.repo;

import java.util.List;

import org.generationitaly.progetto.progetto.entity.Canale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CanaleRepo extends JpaRepository<Canale, Long> {
    public List<Canale> findByNomeCanaleLike(String nomeCanale);
    // public List<Canale> findAllById(List<Long> id);
}
