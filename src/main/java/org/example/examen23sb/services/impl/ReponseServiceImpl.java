package org.example.examen23sb.services.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.examen23sb.entities.Reponse;
import org.example.examen23sb.repositories.ReponseRepository;
import org.example.examen23sb.services.ReponseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ReponseServiceImpl implements ReponseService {

    private final ReponseRepository reponseRepository;

    @Override
    public Reponse save(Reponse reponse) {
        return reponseRepository.save(reponse);
    }

    @Override
    public List<Reponse> findAll() {
        return reponseRepository.findAll();
    }

    @Override
    public Optional<Reponse> findById(Integer id) {
        return reponseRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        log.debug("Deleting reponse with id={}", id);
        reponseRepository.deleteById(id);
    }
}
