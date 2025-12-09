package org.example.examen23sb.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Question {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idQuestion;
    
    private String libelleQ;
    
    @Enumerated(EnumType.STRING)
    private Complexite complexite;
    
    @ManyToOne
    @JsonBackReference
    @ToString.Exclude
    private Quiz quiz;
    
    @OneToMany(fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Reponse> listR;
}
