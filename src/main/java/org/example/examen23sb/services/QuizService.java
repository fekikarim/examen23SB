package org.example.examen23sb.services;

import org.example.examen23sb.entities.Quiz;

import java.util.List;
import java.util.Optional;

public interface QuizService {
    Quiz save(Quiz quiz);

    Quiz ajouterQuiz(Quiz quiz);

    Quiz affecterQuizCandidat(String titreQuiz, Integer idCandidat);

    void recupererQuizPlusDifficile();

    List<Quiz> findAll();

    Optional<Quiz> findById(Integer id);

    void deleteById(Integer id);
}
