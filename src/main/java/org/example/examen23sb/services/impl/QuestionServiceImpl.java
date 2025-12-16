package org.example.examen23sb.services.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.examen23sb.entities.Question;
import org.example.examen23sb.entities.Quiz;
import org.example.examen23sb.repositories.QuestionRepository;
import org.example.examen23sb.repositories.QuizRepository;
import org.example.examen23sb.services.QuestionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final QuizRepository quizRepository;

    @Override
    public Question save(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Question ajouterQuestEtRepEtAffecterQuestAQuiz(Question question, Integer idQuiz) {
        // Fetch the quiz
        Quiz quiz = quizRepository.findById(idQuiz)
                .orElseThrow(() -> new IllegalArgumentException("Quiz not found with id: " + idQuiz));
        
        // Set the quiz for the question
        question.setQuiz(quiz);
        
        // Save the question with its responses
        Question savedQuestion = questionRepository.save(question);
        
        log.info("Question '{}' ajoutée avec {} réponses et affectée au quiz '{}'", 
                savedQuestion.getLibelleQ(), 
                savedQuestion.getListR() != null ? savedQuestion.getListR().size() : 0,
                quiz.getTitreQuiz());
        
        return savedQuestion;
    }

    @Override
    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    @Override
    public Optional<Question> findById(Integer id) {
        return questionRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        log.debug("Deleting question with id={}", id);
        questionRepository.deleteById(id);
    }
}
