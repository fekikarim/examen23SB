package org.example.examen23sb.controllers;

import lombok.AllArgsConstructor;
import org.example.examen23sb.entities.Reponse;
import org.example.examen23sb.services.ReponseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/reponses")
public class ReponseController {

    private final ReponseService reponseService;

    @GetMapping
    public List<Reponse> getAll() {
        return reponseService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reponse> getById(@PathVariable Integer id) {
        return reponseService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Reponse> create(@RequestBody Reponse reponse) {
        Reponse saved = reponseService.save(reponse);
        return ResponseEntity.created(URI.create("/reponses/" + saved.getIdReponse())).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reponse> update(@PathVariable Integer id, @RequestBody Reponse reponse) {
        return reponseService.findById(id)
                .map(existing -> {
                    reponse.setIdReponse(id);
                    Reponse updated = reponseService.save(reponse);
                    return ResponseEntity.ok(updated);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (reponseService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        reponseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
