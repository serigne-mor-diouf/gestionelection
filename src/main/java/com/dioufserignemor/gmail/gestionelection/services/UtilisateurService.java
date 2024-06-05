package com.dioufserignemor.gmail.gestionelection.services;

import com.dioufserignemor.gmail.gestionelection.DTO.UtilisateurDto;
import com.dioufserignemor.gmail.gestionelection.entites.Role;
import com.dioufserignemor.gmail.gestionelection.entites.Utilisateur;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.Optional;

public interface UtilisateurService {
    public Collection<UtilisateurDto> afficherLesUtilisateurs();
    public Optional<Utilisateur> obtenirUnUtilisateurParEmail(String email);

    public UtilisateurDto ajouterUnUtilisateur(UtilisateurDto inscriptionDto);


    public boolean existsByEmail(String email);

    public Utilisateur creerUnMotDePasse(String motDePasse, Utilisateur utilisateur);

    public Collection<Long> listeDesElecteursUtilisateur();

    public Boolean existeParId(Long id);

    Boolean existeParPersonnelId(Long id);

    public Optional<Utilisateur> rechercherParId(Long id);

    public void supprimerUnUtilisateurParId(Long id);

    Collection<Role> afficherLesRolesDeLutilisateur(Long id);

    UtilisateurDto modifierUnUtilisateur(UtilisateurDto utilisateurDto, Long id);

    Utilisateur inverserEtatUtilisateur(Long utilisateurId);

    Utilisateur inverserAdminUtilisateur(Long utilisateurId);

    Utilisateur ajouterRoleAUtilisateur(Long utilisateurId, Collection<Long> listeIdRole);

    Utilisateur retirerRoleAUtilisateur(Long utilisateurId, Collection<Long> listeIdRole);

    ResponseEntity<?> findByNomPrenomPage(int page, int size, String designation);
}
