package org.example.examen23sb.services;

import org.example.examen23sb.entities.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionService {
    Question save(Question question);

    Question ajouterQuestEtRepEtAffecterQuestAQuiz(Question question, Integer idQuiz);

    List<Question> findAll();

    Optional<Question> findById(Integer id);

    void deleteById(Integer id);
}
