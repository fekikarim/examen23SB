package org.example.examen23sb.services;

import org.example.examen23sb.entities.Candidat;

import java.util.List;
import java.util.Optional;

public interface CandidatService {
    Candidat save(Candidat candidat);

    List<Candidat> findAll();

    Optional<Candidat> findById(Integer id);

    void deleteById(Integer id);
}
