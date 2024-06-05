package com.dioufserignemor.gmail.gestionelection.entites;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "electeur")
public class Electeur implements Serializable  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private Civilite civilite = Civilite.MONSIEUR;
    private String adresse;
    private String telephone;
    private String nationalId;
    private LocalDate dateNaissance;
    @OneToOne(mappedBy = "electeur")
    @JsonIgnore
    private Utilisateur utilisateur;

}
