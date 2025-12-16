package org.example.examen23sb.services.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.examen23sb.entities.Candidat;
import org.example.examen23sb.entities.Complexite;
import org.example.examen23sb.entities.Quiz;
import org.example.examen23sb.repositories.CandidatRepository;
import org.example.examen23sb.repositories.QuestionRepository;
import org.example.examen23sb.repositories.QuizRepository;
import org.example.examen23sb.services.QuizService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final CandidatRepository candidatRepository;
    private final QuestionRepository questionRepository;

    @Override
    public Quiz save(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Override
    public Quiz ajouterQuiz(Quiz quiz) {
        log.info("Ajout du quiz: {} - {}", quiz.getTitreQuiz(), quiz.getSpecialite());
        return quizRepository.save(quiz);
    }

    @Override
    public Quiz affecterQuizCandidat(String titreQuiz, Integer idCandidat) {
        Quiz quiz = quizRepository.findByTitreQuiz(titreQuiz)
                .orElseThrow(() -> new IllegalArgumentException("Quiz not found: " + titreQuiz));
        Candidat candidat = candidatRepository.findById(idCandidat)
                .orElseThrow(() -> new IllegalArgumentException("Candidat not found: " + idCandidat));
        
        // Add candidat to quiz's list
        if (quiz.getListC() == null) {
            quiz.setListC(new java.util.ArrayList<>());
        }
        if (!quiz.getListC().contains(candidat)) {
            quiz.getListC().add(candidat);
        }
        
        // Increment candidat's nbQuiz
        Integer currentCount = candidat.getNbQuiz() != null ? candidat.getNbQuiz() : 0;
        candidat.setNbQuiz(currentCount + 1);
        
        log.info("Affectation du quiz '{}' au candidat {} - nbQuiz incremented to {}", 
                titreQuiz, candidat.getPrenom(), candidat.getNbQuiz());
        
        // Save both
        candidatRepository.save(candidat);
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
    @Scheduled(fixedDelay = 30000)
    public void recupererQuizPlusDifficile() {
        try {
            Long quizId = questionRepository.findQuizIdWithMostComplexityQuestions(Complexite.DIFFICILE);
            if (quizId != null) {
                Quiz quiz = quizRepository.findById(quizId.intValue())
                        .orElse(null);
                if (quiz != null) {
                    long difficultCount = quiz.getListQt().stream()
                            .filter(q -> q.getComplexite() == Complexite.DIFFICILE)
                            .count();
                    log.info("========================================");
                    log.info("Quiz le plus difficile: {}", quiz.getTitreQuiz());
                    log.info("Spécialité: {}", quiz.getSpecialite());
                    log.info("Nombre de questions difficiles: {}", difficultCount);
                    log.info("========================================");
                    System.out.println("========================================");
                    System.out.println("Quiz le plus difficile: " + quiz.getTitreQuiz());
                    System.out.println("Spécialité: " + quiz.getSpecialite());
                    System.out.println("Nombre de questions difficiles: " + difficultCount);
                    System.out.println("========================================");
                }
            }
        } catch (Exception e) {
            log.error("Erreur lors de la récupération du quiz le plus difficile", e);
        }
    }

    @Override
    public void deleteById(Integer id) {
        log.debug("Deleting quiz with id={}", id);
        quizRepository.deleteById(id);
    }
}
