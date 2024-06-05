package com.dioufserignemor.gmail.gestionelection.implementations;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dioufserignemor.gmail.gestionelection.DTO.ElecteurDTO;
import com.dioufserignemor.gmail.gestionelection.entites.Electeur;
import com.dioufserignemor.gmail.gestionelection.repositories.ElecteurRepository;
import com.dioufserignemor.gmail.gestionelection.services.ElecteurService;

@Service
public class ElecteurServiceImpl implements ElecteurService {
@Autowired
private ElecteurRepository electeurRepository ;


    @Override
    public Electeur getElecteurById(Long electeurId) {
        return electeurRepository.findById(electeurId).orElse(null) ;
    }

    @Override
    public List<Electeur> getAllElecteurs() {
        return electeurRepository.findAll() ;
    }

    @Override
    public Electeur updateElecteur(Long electeurId, ElecteurDTO electeurDTO) {
        Electeur existeElecteur = electeurRepository.findById(electeurId)
                .orElseThrow(() -> new RuntimeException("Électeur non trouvé pour l'ID : " + electeurId));

        // Mettre à jour les attributs nécessaires avec les setters
        existeElecteur.setNom(electeurDTO.getNom());
        existeElecteur.setNom(electeurDTO.getPrenom());
        existeElecteur.setNationalId(electeurDTO.getNationalId());
        existeElecteur.setDateNaissance(electeurDTO.getDateNaissance());

        return electeurRepository.save(existeElecteur);
    }

    @Override
    public void deleteElecteur(Long electeurId) {
         if (!electeurRepository.existsById(electeurId)) {
            throw new NoSuchElementException("eleteur non trouvé avec l'ID : " + electeurId);
        }
        electeurRepository.deleteById(electeurId);
    }

    @Override
    public Electeur loginVoter(ElecteurDTO electeurDTO) {
        Electeur electeur  = new Electeur();
        if (electeur == null) {
            throw new NoSuchElementException("Électeur non trouvé avec les informations d'identification fournies.");
        }
        return electeur;
    }


    @Override
    public Electeur register(ElecteurDTO electeurDTO) {
        Electeur electeur = new Electeur();
        electeur.setNom(electeurDTO.getNom());
        electeur.setNom(electeurDTO.getPrenom());
        electeur.setNationalId(electeurDTO.getNationalId());
        electeur.setDateNaissance(electeurDTO.getDateNaissance());

        return electeurRepository.save(electeur);
    }
    
}
