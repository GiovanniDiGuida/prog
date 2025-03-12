package org.generationitaly.progetto.progetto.repo;

import org.generationitaly.progetto.progetto.entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtenteRepo extends JpaRepository<Utente, Long>{
    
    }
