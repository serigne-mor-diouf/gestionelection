package com.dioufserignemor.gmail.gestionelection.implementations;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dioufserignemor.gmail.gestionelection.DTO.CandidatDTO;
import com.dioufserignemor.gmail.gestionelection.entites.Candidat;
import com.dioufserignemor.gmail.gestionelection.repositories.CandidatRepository;
import com.dioufserignemor.gmail.gestionelection.services.CandidatService;

@Service
public class CandidatServiceImpl implements CandidatService {

    @Autowired
    private CandidatRepository candidatRepository;

    @Override
    public Candidat getCandidatById(Long candidatId) {
        return candidatRepository.findById(candidatId).orElse(null);
    }
    
//liste des candidat
    @Override
    public List<Candidat> getAllCandidats() {
        return candidatRepository.findAll();
    }
//sup canditat
    @Override
    public void deleteCandidat(Long candidatId) {
        // Vérification si le candidat existe avant de le supprimer
        if (!candidatRepository.existsById(candidatId)) {
            throw new NoSuchElementException("Candidat non trouvé avec l'ID : " + candidatId);
        }
        candidatRepository.deleteById(candidatId);
    }

    public Candidat createCandidat(CandidatDTO candidatDTO) {
        Candidat candidat = new Candidat();
        // Setters pour attributs de candidatDTO
        candidat.setName(candidatDTO.getName());
        candidat.setParty(candidatDTO.getParty());
        // Autres attributs...

        return candidatRepository.save(candidat);
    }


   
    @Override
    public Candidat updateCandidat(Long candidatId, CandidatDTO candidatDTO) {
        Candidat existingCandidat = candidatRepository.findById(candidatId)
                .orElseThrow(() -> new RuntimeException("Candidat non trouvé pour l'ID : " + candidatId));
        existingCandidat.setName(candidatDTO.getName());
        existingCandidat.setParty(candidatDTO.getParty());

        return candidatRepository.save(existingCandidat);
    }

}
