package org.example.examen23sb.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Quiz {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idQuiz;
    
    private String titreQuiz;
    
    private String specialite;
    
    private LocalDate dateQuiz;
    
    @OneToMany(mappedBy = "quiz")
    private List<Question> listQt;
    
    @ManyToMany
    private List<Candidat> listC;
}
