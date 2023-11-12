package com.dioufserignemor.gmail.gestionelection.services;


import java.util.List;

import com.dioufserignemor.gmail.gestionelection.DTO.ElecteurDTO;
import com.dioufserignemor.gmail.gestionelection.entites.Electeur;

public interface ElecteurService {
   // Electeur createElecteur(ElecteurDTO electeurDTO);
    Electeur  getElecteurById(Long electeurId);
    List<Electeur> getAllElecteurs();
    Electeur updateElecteur(Long electeurId, ElecteurDTO electeurDTO);
    void deleteElecteur(Long electeurId);
    Electeur register(ElecteurDTO voterDTO);
    Electeur loginVoter(ElecteurDTO voterDTO);
}
