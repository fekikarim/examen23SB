package org.example.examen23sb.entities;

import jakarta.persistence.*;

@Entity
public class Reponse {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReponse;
    
    private String libelleR;

    public Reponse() {
    }

    public Reponse(String libelleR) {
        this.libelleR = libelleR;
    }

    public Integer getIdReponse() {
        return idReponse;
    }

    public void setIdReponse(Integer idReponse) {
        this.idReponse = idReponse;
    }

    public String getLibelleR() {
        return libelleR;
    }

    public void setLibelleR(String libelleR) {
        this.libelleR = libelleR;
    }
}
