package com.dioufserignemor.gmail.gestionelection.entites;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Vote implements Serializable  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "electeur_id")
    private Electeur electeur;

    @ManyToOne
    @JoinColumn(name = "candidat_id")
    private Candidat candidat;

    public Vote() {
    }

    public Vote(Long id, Electeur electeur, Candidat candidat) {
        this.id = id;
        this.electeur = electeur;
        this.candidat = candidat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Electeur getElecteur() {
        return electeur;
    }

    public void setElecteur(Electeur electeur) {
        this.electeur = electeur;
    }

    public Candidat getCandidat() {
        return candidat;
    }

    public void setCandidat(Candidat candidat) {
        this.candidat = candidat;
    }

    
}
