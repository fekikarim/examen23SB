package org.example.examen23sb.repositories;

import org.example.examen23sb.entities.Complexite;
import org.example.examen23sb.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    @Query("SELECT q.quiz.idQuiz, COUNT(q.idQuestion) FROM Question q WHERE q.complexite = :complexite AND q.quiz IS NOT NULL GROUP BY q.quiz.idQuiz ORDER BY COUNT(q.idQuestion) DESC LIMIT 1")
    Long findQuizIdWithMostComplexityQuestions(@Param("complexite") Complexite complexite);
}
