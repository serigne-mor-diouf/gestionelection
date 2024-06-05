package com.dioufserignemor.gmail.gestionelection.controllers;

import com.dioufserignemor.gmail.gestionelection.DTO.RoleDto;
import com.dioufserignemor.gmail.gestionelection.entites.Role;
import com.dioufserignemor.gmail.gestionelection.services.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("roles")
@AllArgsConstructor
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/enregistrer")
    public ResponseEntity<RoleDto> enregistrerUnRole(@RequestBody RoleDto roleDto) {
        RoleDto roleEnregistre = roleService.enregistrerUnRole(roleDto);
        return ResponseEntity.ok(roleEnregistre);
    }

    @PutMapping(value = "update/{id}")
    public ResponseEntity<RoleDto> updateRole(@Validated @RequestBody RoleDto roleDto , Long id) {
        return ResponseEntity.ok().body(roleService.modifierUnRole(roleDto , id));
    }

    public ResponseEntity<Collection<RoleDto>> afficherlesRoles(){
        return ResponseEntity.ok().body(roleService.afficherLesRole());
    }
}
