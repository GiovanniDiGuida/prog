package org.generationitaly.progetto.progetto.repo;

import org.generationitaly.progetto.progetto.entity.Programma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgrammaRepo extends JpaRepository<Programma, Long>{

}
