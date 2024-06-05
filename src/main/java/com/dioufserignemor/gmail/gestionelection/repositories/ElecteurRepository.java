package com.dioufserignemor.gmail.gestionelection.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dioufserignemor.gmail.gestionelection.entites.Electeur;

public interface ElecteurRepository extends JpaRepository<Electeur , Long>{
    //Electeur findByUsernameAndPassword(String username, String password);
}
