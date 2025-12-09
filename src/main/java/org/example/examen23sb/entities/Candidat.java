package org.example.examen23sb.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Candidat {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCandidat;
    
    private String nom;
    
    private String prenom;
    
    private Integer nbQuiz;
    
    @Enumerated(EnumType.STRING)
    private Niveau niveau;
    
    @ManyToMany(mappedBy = "listC")
    private List<Quiz> listQ;
}
