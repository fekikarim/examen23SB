package org.example.examen23sb.services.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.examen23sb.entities.Quiz;
import org.example.examen23sb.repositories.QuizRepository;
import org.example.examen23sb.services.QuizService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;

    @Override
    public Quiz save(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Override
    public List<Quiz> findAll() {
        return quizRepository.findAll();
    }

    @Override
    public Optional<Quiz> findById(Integer id) {
        return quizRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        log.debug("Deleting quiz with id={}", id);
        quizRepository.deleteById(id);
    }
}
