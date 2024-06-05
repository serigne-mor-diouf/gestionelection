package com.dioufserignemor.gmail.gestionelection.repositories;

import com.dioufserignemor.gmail.gestionelection.entites.Electeur;
import com.dioufserignemor.gmail.gestionelection.entites.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    public boolean existsById(Long id);
    public boolean existsByCode(String code);
    public boolean existsByDescription(String description);
    public void deleteById(Long id);
    public Optional<Permission> findByCode(String nom);
    public Collection<Permission> findByIdIn(Collection<Long> id);

    @Query("SELECT p FROM Permission p WHERE " +
            "(:code IS NULL OR p.code LIKE %:code%) ")
    Page<Permission> findPermissionByCodePage(Pageable pageable, @Param("code") String code);

    @Query("SELECT p FROM Permission p")
    Page<Permission> findPermission(Pageable pageable);

    @Query(value = "SELECT e FROM Electeur e WHERE e.id NOT IN ?1")
    public Set<Electeur> electeurNonUtilisateur(Collection<Long> id);

    //findByCitiesIn
    @Query("SELECT p FROM Permission p WHERE p.code LIKE %:code%")
    List<Permission> findPermissionByCode(@Param("code") String code);

    @Query(value = "SELECT COUNT(*) AS total " +
            "FROM permission p ", nativeQuery = true)
    int nombreTotalPermission();

}
