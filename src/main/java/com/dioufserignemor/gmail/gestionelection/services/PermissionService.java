package com.dioufserignemor.gmail.gestionelection.services;

import com.dioufserignemor.gmail.gestionelection.DTO.PermissionDto;
import com.dioufserignemor.gmail.gestionelection.entites.Permission;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface PermissionService {

    public Boolean existeParId(Long id);
    public Boolean existeParCode(String code);
    public Collection<PermissionDto> afficherLesPermissions();
    public Optional<Permission> rechercherParId(Long id);
    public PermissionDto enregistrerUnePermission(PermissionDto permissionDto);
    public void supprimerUnePermissionParId(Long id);
    public Optional<Permission> rechercherParCode(String code);
    public Boolean existeParDescription(String description);

   PermissionDto modifierUnePermission(PermissionDto permissionDto, Long id);

    List<Permission> findByCode(String code);

    ResponseEntity<?> findByCodePage(int page, int size, String code);
}
