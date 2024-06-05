package com.dioufserignemor.gmail.gestionelection.services;

import com.dioufserignemor.gmail.gestionelection.DTO.RoleDto;
import com.dioufserignemor.gmail.gestionelection.entites.Permission;
import com.dioufserignemor.gmail.gestionelection.entites.Role;
import org.springframework.http.ResponseEntity;


import java.util.Collection;
import java.util.Optional;

public interface RoleService {
    public Collection<RoleDto> afficherLesRole();

    public Optional<Role> rechercherUnRoleParId(Long id) ;

    public Role recupererUnRoleParId(Long id) ;

    public Boolean existeRoleParNom(String nom);

    public Boolean existeRoleParId(Long id) ;

    public RoleDto enregistrerUnRole(RoleDto roleDto);


    public RoleDto modifierUnRole(RoleDto roleDto , Long id);

    public Role ajouterPermissionARole(Long idRole , Collection<Long> listeidPermission);

    public Role retirerPermissionARole(Long idRole , Collection<Long> listeidPermission);

    public void suprimerRoleid(Long idRole);

    public Optional<Role> rechercherParNom(String nom);

    public Collection<Permission> afficherLesPermissionsDuGroupe(Long id);

    public Collection<Role>findByNom(String nom);

    public ResponseEntity<?> findNomPage(int page , int size , String nom);

    public Role trouverRoleParId(Long roleId);
}
