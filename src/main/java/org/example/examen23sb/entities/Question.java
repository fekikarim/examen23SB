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
public class Question {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idQuestion;
    
    private String libelleQ;
    
    @Enumerated(EnumType.STRING)
    private Complexite complexite;
    
    @ManyToOne
    private Quiz quiz;
    
    @OneToMany
    private List<Reponse> listR;
}
