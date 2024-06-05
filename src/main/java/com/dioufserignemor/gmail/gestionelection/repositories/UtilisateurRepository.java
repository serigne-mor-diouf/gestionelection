package com.dioufserignemor.gmail.gestionelection.repositories;

import com.dioufserignemor.gmail.gestionelection.entites.Electeur;
import com.dioufserignemor.gmail.gestionelection.entites.Utilisateur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur , Long> {
    public boolean existsById(Long id);

    public boolean existsByElecteurId(Long id);

    public void deleteById(Long id);

    public Optional<Utilisateur> findByEmail(String email);

    public Boolean existsByEmail(String email);

    Boolean existsByElecteur(Electeur electeur);
    @Query(value = "select u.electeur.id FROM Utilisateur u")
    public Collection<Long> listeDesElecteurtilisateur();


    @Query("SELECT u FROM Utilisateur u JOIN u.electeur p WHERE (p.nom LIKE %:designation% OR p.prenom LIKE %:designation%)")
    Page<Utilisateur> findByElecteurNomAndPrenom(Pageable pageable, @Param("designation") String designation);

    @Query(value = "SELECT COUNT(*) AS total " +
            "FROM utilisateur u ", nativeQuery = true)
    int nombreTotalUtilisateur();

    @Query(value = "SELECT COUNT(*) AS total " +
            "FROM utilisateur u " +
            "WHERE u.est_actif = 1", nativeQuery = true)
    int nombreTotalUtilisateurActif();

    @Query(value = "SELECT COUNT(*) AS total " +
            "FROM utilisateur u " +
            "WHERE u.est_actif = 0", nativeQuery = true)
    int nombreTotalUtilisateurNonActif();

}
