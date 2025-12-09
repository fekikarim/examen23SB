package org.example.examen23sb.services.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.examen23sb.entities.Candidat;
import org.example.examen23sb.repositories.CandidatRepository;
import org.example.examen23sb.services.CandidatService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class CandidatServiceImpl implements CandidatService {

    private final CandidatRepository candidatRepository;

    @Override
    public Candidat save(Candidat candidat) {
        return candidatRepository.save(candidat);
    }

    @Override
    public List<Candidat> findAll() {
        return candidatRepository.findAll();
    }

    @Override
    public Optional<Candidat> findById(Integer id) {
        return candidatRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        log.debug("Deleting candidat with id={}", id);
        candidatRepository.deleteById(id);
    }
}
