package com.dioufserignemor.gmail.gestionelection.repositories;

import com.dioufserignemor.gmail.gestionelection.entites.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByNom(String nom);
    boolean existsByNom(String nom);
    boolean existsById(Long id);

    @Query("SELECT r FROM Role r WHERE " +
            "(:nom IS NULL OR r.nom LIKE %:nom%) ")
    Page<Role> findRoleByNomPage(Pageable pageable, @Param("nom") String nom);

    @Query("SELECT r FROM Role r WHERE r.nom LIKE %:nom%")
    List<Role> findRoleByNom(@Param("nom") String nom);

    @Query(value = "SELECT COUNT(*) AS total " +
            "FROM role_utilisateur r ", nativeQuery = true)
    int nombreTotalRole();
}
