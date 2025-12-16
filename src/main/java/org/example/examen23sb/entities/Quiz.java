package org.example.examen23sb.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    
    @OneToMany(mappedBy = "quiz", fetch = FetchType.EAGER)
    @JsonManagedReference
    @ToString.Exclude
    private List<Question> listQt;
    
    @ManyToMany
    @JoinTable(
        name = "quiz_listc",
        joinColumns = @JoinColumn(name = "quiz_id_quiz"),
        inverseJoinColumns = @JoinColumn(name = "listc_id_candidat")
    )
    @JsonIgnore
    @ToString.Exclude
    private List<Candidat> listC;
}
