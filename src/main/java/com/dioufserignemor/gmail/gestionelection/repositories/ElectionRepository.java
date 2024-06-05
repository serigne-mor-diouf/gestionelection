package com.dioufserignemor.gmail.gestionelection.repositories;

import com.dioufserignemor.gmail.gestionelection.entites.Election;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElectionRepository extends JpaRepository<Election, Long> {
}
