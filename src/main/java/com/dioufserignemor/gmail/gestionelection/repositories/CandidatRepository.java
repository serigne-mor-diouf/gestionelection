package com.dioufserignemor.gmail.gestionelection.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dioufserignemor.gmail.gestionelection.entites.Candidat;

public interface CandidatRepository extends JpaRepository<Candidat , Long> {
    
}
