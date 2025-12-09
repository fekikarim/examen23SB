package org.example.examen23sb.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
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

    public Quiz() {
    }

    public Quiz(String titreQuiz, String specialite, LocalDate dateQuiz) {
        this.titreQuiz = titreQuiz;
        this.specialite = specialite;
        this.dateQuiz = dateQuiz;
    }

    public Integer getIdQuiz() {
        return idQuiz;
    }

    public void setIdQuiz(Integer idQuiz) {
        this.idQuiz = idQuiz;
    }

    public String getTitreQuiz() {
        return titreQuiz;
    }

    public void setTitreQuiz(String titreQuiz) {
        this.titreQuiz = titreQuiz;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public LocalDate getDateQuiz() {
        return dateQuiz;
    }

    public void setDateQuiz(LocalDate dateQuiz) {
        this.dateQuiz = dateQuiz;
    }

    public List<Question> getListQt() {
        return listQt;
    }

    public void setListQt(List<Question> listQt) {
        this.listQt = listQt;
    }

    public List<Candidat> getListC() {
        return listC;
    }

    public void setListC(List<Candidat> listC) {
        this.listC = listC;
    }
}
