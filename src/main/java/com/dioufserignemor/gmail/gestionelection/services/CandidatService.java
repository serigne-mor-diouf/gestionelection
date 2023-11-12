package com.dioufserignemor.gmail.gestionelection.services;

import java.util.List;

import com.dioufserignemor.gmail.gestionelection.DTO.CandidatDTO;
import com.dioufserignemor.gmail.gestionelection.entites.Candidat;
public interface CandidatService {
    Candidat createCandidat(CandidatDTO candidatDTO);
    Candidat getCandidatById(Long candidatId);
    List<Candidat> getAllCandidats();
    Candidat updateCandidat(Long candidatId, CandidatDTO candidatDTO);
    void deleteCandidat(Long candidatId);
}
