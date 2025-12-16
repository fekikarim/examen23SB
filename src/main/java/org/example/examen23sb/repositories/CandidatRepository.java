package org.example.examen23sb.repositories;

import org.example.examen23sb.entities.Candidat;
import org.example.examen23sb.entities.Niveau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidatRepository extends JpaRepository<Candidat, Integer> {
    @Query("SELECT DISTINCT c FROM Candidat c JOIN c.listQ q WHERE c.niveau = :niveau AND q.specialite = :specialite")
    List<Candidat> recupererCandidat(@Param("specialite") String specialite, @Param("niveau") Niveau niveau);
}
