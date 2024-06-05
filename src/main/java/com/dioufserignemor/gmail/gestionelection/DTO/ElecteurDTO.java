package com.dioufserignemor.gmail.gestionelection.DTO;

import com.dioufserignemor.gmail.gestionelection.entites.Civilite;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ElecteurDTO {
    private Long id;

    private String nom;

    private String prenom;

    private String nationalId;

    private LocalDate dateNaissance;

    private Civilite civilite = Civilite.MONSIEUR;

    private String adresse;

    private String telephone;
}
