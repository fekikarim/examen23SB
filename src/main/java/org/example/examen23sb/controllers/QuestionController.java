package org.example.examen23sb.controllers;

import lombok.AllArgsConstructor;
import org.example.examen23sb.entities.Question;
import org.example.examen23sb.services.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping
    public List<Question> getAll() {
        return questionService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> getById(@PathVariable Integer id) {
        return questionService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Question> create(@RequestBody Question question) {
        Question saved = questionService.save(question);
        return ResponseEntity.created(URI.create("/questions/" + saved.getIdQuestion())).body(saved);
    }

    @PostMapping("/ajouter-avec-reponses")
    public ResponseEntity<Question> ajouterQuestEtRepEtAffecterQuestAQuiz(@RequestBody Question question, @RequestParam Integer idQuiz) {
        Question saved = questionService.ajouterQuestEtRepEtAffecterQuestAQuiz(question, idQuiz);
        return ResponseEntity.created(URI.create("/questions/" + saved.getIdQuestion())).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Question> update(@PathVariable Integer id, @RequestBody Question question) {
        return questionService.findById(id)
                .map(existing -> {
                    question.setIdQuestion(id);
                    Question updated = questionService.save(question);
                    return ResponseEntity.ok(updated);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (questionService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        questionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
