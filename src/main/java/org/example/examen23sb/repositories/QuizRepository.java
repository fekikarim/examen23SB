package org.example.examen23sb.repositories;

import org.example.examen23sb.entities.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {
    Optional<Quiz> findByTitreQuiz(String titreQuiz);
}
