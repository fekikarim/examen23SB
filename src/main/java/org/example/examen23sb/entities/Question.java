package org.example.examen23sb.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
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

    public Question() {
    }

    public Question(String libelleQ, Complexite complexite) {
        this.libelleQ = libelleQ;
        this.complexite = complexite;
    }

    public Integer getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(Integer idQuestion) {
        this.idQuestion = idQuestion;
    }

    public String getLibelleQ() {
        return libelleQ;
    }

    public void setLibelleQ(String libelleQ) {
        this.libelleQ = libelleQ;
    }

    public Complexite getComplexite() {
        return complexite;
    }

    public void setComplexite(Complexite complexite) {
        this.complexite = complexite;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public List<Reponse> getListR() {
        return listR;
    }

    public void setListR(List<Reponse> listR) {
        this.listR = listR;
    }
}
