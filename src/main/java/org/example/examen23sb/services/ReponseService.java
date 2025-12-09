package org.example.examen23sb.services;

import org.example.examen23sb.entities.Reponse;

import java.util.List;
import java.util.Optional;

public interface ReponseService {
    Reponse save(Reponse reponse);

    List<Reponse> findAll();

    Optional<Reponse> findById(Integer id);

    void deleteById(Integer id);
}
