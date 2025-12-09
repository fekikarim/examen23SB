package org.example.examen23sb.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Reponse {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReponse;
    
    private String libelleR;
}
