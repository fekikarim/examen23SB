package org.example.examen23sb.services;

import org.example.examen23sb.entities.Candidat;
import org.example.examen23sb.entities.Niveau;

import java.util.List;
import java.util.Optional;

public interface CandidatService {
    Candidat save(Candidat candidat);

    Candidat ajouterCandidat(Candidat candidat);

    List<Candidat> recupererCandidat(String specialite, Niveau niveau);

    List<Candidat> findAll();

    Optional<Candidat> findById(Integer id);

    void deleteById(Integer id);
}
