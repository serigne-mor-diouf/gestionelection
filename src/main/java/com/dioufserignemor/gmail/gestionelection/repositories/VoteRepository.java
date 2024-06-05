package com.dioufserignemor.gmail.gestionelection.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dioufserignemor.gmail.gestionelection.entites.Electeur;
import com.dioufserignemor.gmail.gestionelection.entites.Vote;

public interface VoteRepository extends JpaRepository<Vote , Long> {
    //boolean existsByElecteur(Electeur electeur);

    @Query(value = "SELECT c.name AS candidatName, COUNT(v.id) AS votes " +
    "FROM Candidat c " +
    "JOIN Vote v ON c.id = v.candidat.id " +
    "GROUP BY c.name " +
    "ORDER BY votes DESC " +
    "LIMIT 3", nativeQuery = true)
    List<Object[]> vainqueureTopCandidates();

    List<Vote> findByElectionId(Long electionId);
    List<Vote> findByElectionIdAndCandidatId(Long electionId, Long candidatId);
}
