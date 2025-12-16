package org.example.examen23sb.controllers;

import lombok.AllArgsConstructor;
import org.example.examen23sb.entities.Candidat;
import org.example.examen23sb.entities.Niveau;
import org.example.examen23sb.services.CandidatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/candidats")
public class CandidatController {

    private final CandidatService candidatService;

    @GetMapping
    public List<Candidat> getAll() {
        return candidatService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidat> getById(@PathVariable Integer id) {
        return candidatService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Candidat> create(@RequestBody Candidat candidat) {
        Candidat saved = candidatService.save(candidat);
        return ResponseEntity.created(URI.create("/candidats/" + saved.getIdCandidat())).body(saved);
    }

    @PostMapping("/ajouter")
    public ResponseEntity<Candidat> ajouterCandidat(@RequestBody Candidat candidat) {
        Candidat saved = candidatService.ajouterCandidat(candidat);
        return ResponseEntity.created(URI.create("/candidats/" + saved.getIdCandidat())).body(saved);
    }

    @GetMapping("/recuperer")
    public List<Candidat> recupererCandidat(@RequestParam String specialite, @RequestParam Niveau niveau) {
        return candidatService.recupererCandidat(specialite, niveau);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Candidat> update(@PathVariable Integer id, @RequestBody Candidat candidat) {
        return candidatService.findById(id)
                .map(existing -> {
                    candidat.setIdCandidat(id);
                    Candidat updated = candidatService.save(candidat);
                    return ResponseEntity.ok(updated);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (candidatService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        candidatService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
