package org.example.examen23sb.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
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

    public Candidat() {
    }

    public Candidat(String nom, String prenom, Integer nbQuiz, Niveau niveau) {
        this.nom = nom;
        this.prenom = prenom;
        this.nbQuiz = nbQuiz;
        this.niveau = niveau;
    }

    public Integer getIdCandidat() {
        return idCandidat;
    }

    public void setIdCandidat(Integer idCandidat) {
        this.idCandidat = idCandidat;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Integer getNbQuiz() {
        return nbQuiz;
    }

    public void setNbQuiz(Integer nbQuiz) {
        this.nbQuiz = nbQuiz;
    }

    public Niveau getNiveau() {
        return niveau;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }

    public List<Quiz> getListQ() {
        return listQ;
    }

    public void setListQ(List<Quiz> listQ) {
        this.listQ = listQ;
    }
}
