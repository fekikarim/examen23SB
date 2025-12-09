package org.example.examen23sb.controllers;

import lombok.AllArgsConstructor;
import org.example.examen23sb.entities.Quiz;
import org.example.examen23sb.services.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/quiz")
public class QuizController {

    private final QuizService quizService;

    @GetMapping
    public List<Quiz> getAll() {
        return quizService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getById(@PathVariable Integer id) {
        return quizService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Quiz> create(@RequestBody Quiz quiz) {
        Quiz saved = quizService.save(quiz);
        return ResponseEntity.created(URI.create("/quiz/" + saved.getIdQuiz())).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Quiz> update(@PathVariable Integer id, @RequestBody Quiz quiz) {
        return quizService.findById(id)
                .map(existing -> {
                    quiz.setIdQuiz(id);
                    Quiz updated = quizService.save(quiz);
                    return ResponseEntity.ok(updated);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (quizService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        quizService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
