package com.dioufserignemor.gmail.gestionelection.implementations;

import com.dioufserignemor.gmail.gestionelection.DTO.RoleDto;
import com.dioufserignemor.gmail.gestionelection.entites.Permission;
import com.dioufserignemor.gmail.gestionelection.entites.Role;
import com.dioufserignemor.gmail.gestionelection.exception.EntityNotFoundException;
import com.dioufserignemor.gmail.gestionelection.exception.RequestException;
import com.dioufserignemor.gmail.gestionelection.repositories.PermissionRepository;
import com.dioufserignemor.gmail.gestionelection.repositories.RoleRepository;
import com.dioufserignemor.gmail.gestionelection.services.RoleService;
import com.dioufserignemor.gmail.gestionelection.services.UtilisateurService;
import jakarta.transaction.Transactional;
import mappers.GenericMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private  PermissionRepository permissionRepository;

    private GenericMapper<Role, RoleDto> roleMapper;


    MessageSource messageSource;

    private static final Logger logger = LogManager.getLogger(RoleService.class);

    @Override
    public Collection<RoleDto> afficherLesRole() {
        return roleRepository.findAll().stream()
                .map(role -> roleMapper.entityToDto(role, RoleDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Role> rechercherUnRoleParId(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public Role recupererUnRoleParId(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("L'id da role non trovato"));
    }

    @Override
    public Boolean existeRoleParNom(String nom) {
        return roleRepository.existsByNom(nom);
    }

    @Override
    public Boolean existeRoleParId(Long id) {
        return roleRepository.existsById(id);
    }

    @Transactional
    public RoleDto enregistrerUnRole(RoleDto roleDto) {
        Role roleEntity = roleMapper.dtoToEntity(roleDto, Role.class);

        // Récupérer les permissions à partir de leurs IDs
        Collection<Long> listeIdPermission = roleDto.getPermissions();
        Collection<Permission> permissions = permissionRepository.findAllById(listeIdPermission);

        if (permissions.size() != listeIdPermission.size()) {
            throw new EntityNotFoundException("Une ou plusieurs permissions n'ont pas été trouvées.");
        }

        // Mise à jour des permissions
        roleEntity.setPermissions(permissions);

        // Sauvegarde du rôle
        Role role = roleRepository.save(roleEntity);

        return roleMapper.entityToDto(role, RoleDto.class);
    }

    @Override
    public RoleDto modifierUnRole(RoleDto roleDto ,  Long id) {
        return roleRepository.findById(id).map(role -> {
            // Utiliser le mapper pour mettre à jour les champs du rôle avec les valeurs du DTO
            Role roleMiseAJour = roleMapper.dtoToEntity(roleDto, Role.class);

            // Récupération des permissions à partir des identifiants dans le DTO
            Collection<Long> listeIdPermission = roleDto.getPermissions();
            if(listeIdPermission != null) {
                Collection<Permission> permissions = permissionRepository.findAllById(listeIdPermission);
                // Mise à jour des permissions associées au rôle
                roleMiseAJour.setPermissions(permissions);
            }
            // Sauvegarde du rôle mis à jour
            Role roleMiseAJourEnBase = roleRepository.save(roleMiseAJour);

            // Retourne le rôle mis à jour en tant que DTO
            return roleMapper.entityToDto(roleMiseAJourEnBase, RoleDto.class);
        }).orElseThrow(() -> new EntityNotFoundException(
                messageSource.getMessage("role non trouvé", new Object[]{id}, Locale.getDefault())));
    }


    @Override
    public Role ajouterPermissionARole(Long idRole, Collection<Long> listeidPermission) {
        Role role = roleRepository.findById(idRole)
                .orElseThrow(()-> new EntityNotFoundException("role non trouve"));

        Collection<Permission> nouveauxPermissions = permissionRepository.findAllById(listeidPermission);
        List<Permission> ancienRoles = new ArrayList<>(role.getPermissions());

        nouveauxPermissions.removeIf(ancienRoles::contains);
        if(nouveauxPermissions.isEmpty()) {
            return role ;
        }
        role.getPermissions().addAll(nouveauxPermissions);
        return roleRepository.save(role);
    }

    @Override
    public Role retirerPermissionARole(Long idRole, Collection<Long> listeidPermission) {
        Role role = roleRepository.findById(idRole)
                .orElseThrow(()-> new EntityNotFoundException("role non trouve"));

        Collection<Permission> permissionsRetirer = permissionRepository.findAllById(listeidPermission);
        List<Permission> anciensPermssions = new ArrayList<>(role.getPermissions());
        boolean toutLesRolesExiste = new HashSet<>(anciensPermssions).containsAll(permissionsRetirer);

        if(!toutLesRolesExiste) {
            throw new IllegalArgumentException("Certains des IDs de permission spécifiés ne correspondent pas aux permissions du rôle.");
        }

        role.getPermissions().removeAll(permissionsRetirer);
        return roleRepository.save(role);
    }

    @Override
    public void suprimerRoleid(Long idRole) {
        if (roleRepository.findById(idRole).isPresent()) {
            try {
                roleRepository.deleteById(idRole);
                logger.info("Le role avec l'ID {} a été supprimé.", idRole);

            } catch (Exception exception) {
                logger.error("Erreur lors de la suppression du role avec l'ID {} : {}", idRole, exception.getMessage());

                throw new RequestException(
                        messageSource.getMessage("role.errorDeletion", new Object[]{idRole},
                                Locale.getDefault()),
                        HttpStatus.CONFLICT
                );
            }
        } else {
            logger.warn("Tentative de suppression du role avec l'ID {} qui n'existe pas.", idRole);

            throw new EntityNotFoundException(messageSource.getMessage("role.notFound", new Object[]{idRole},
                    Locale.getDefault()));
        }
    }

    @Override
    public Optional<Role> rechercherParNom(String nom) {
        return roleRepository.findByNom(nom);
    }

    @Override
    public Collection<Permission> afficherLesPermissionsDuGroupe(Long id) {
        return roleRepository.findById(id).get().getPermissions();
    }

    @Override
    public Collection<Role> findByNom(String nom) {
        return roleRepository.findRoleByNom(nom);
    }

    @Override
    public ResponseEntity<?> findNomPage(int page, int size, String nom) {
        if (nom == null){
            return ResponseEntity.badRequest().body(null);
        }
        Pageable pageable = PageRequest.of(page , size , Sort.by("id").descending()) ;
        return ResponseEntity.ok(roleRepository
                .findRoleByNomPage(pageable, nom));
    }

    @Override
    public Role trouverRoleParId(Long roleId) {
        return roleRepository.findById(roleId)
                .orElseThrow(()->new IllegalArgumentException("role est introuvable"));
    }
}
