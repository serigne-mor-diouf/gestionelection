package com.dioufserignemor.gmail.gestionelection.implementations;

import com.dioufserignemor.gmail.gestionelection.DTO.PermissionDto;
import com.dioufserignemor.gmail.gestionelection.entites.Permission;
import com.dioufserignemor.gmail.gestionelection.exception.EntityNotFoundException;
import com.dioufserignemor.gmail.gestionelection.exception.RequestException;
import com.dioufserignemor.gmail.gestionelection.repositories.PermissionRepository;
import com.dioufserignemor.gmail.gestionelection.services.PermissionService;
import com.dioufserignemor.gmail.gestionelection.services.UtilisateurService;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    GenericMapper<Permission, PermissionDto> mapper;

    private PasswordEncoder passwordEncoder;

    MessageSource messageSource;

    private static final Logger logger = LogManager.getLogger(UtilisateurService.class);


    @Override
    public Boolean existeParId(Long id) {
        return permissionRepository.existsById(id);
    }

    @Override
    public Boolean existeParCode(String code) {
        return permissionRepository.existsByCode(code);
    }

    @Override
    public Collection<PermissionDto> afficherLesPermissions() {
        return permissionRepository.findAll()
                .stream()
                .map(permission -> mapper.entityToDto(permission, PermissionDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Permission> rechercherParId(Long id) {
        return permissionRepository.findById(id);
    }

    @Override
    public PermissionDto enregistrerUnePermission(PermissionDto permissionDto) {
        Permission permissionEntity = mapper.dtoToEntity(permissionDto, Permission.class);
        Permission permission = permissionRepository.save(permissionEntity);
        return mapper.entityToDto(permission, PermissionDto.class);
    }

    @Override
    public void supprimerUnePermissionParId(Long id) {
        if (permissionRepository.existsById(id)) {
            try {
                permissionRepository.deleteById(id);
                logger.info("La permission avec l'ID {} a été supprimé.", id);

            } catch (Exception exception) {
                logger.error("Erreur lors de la suppression de la permission avec l'ID {} : {}", id, exception.getMessage());

                throw new RequestException(
                        messageSource.getMessage("permission.errorDeletion", new Object[]{id},
                                Locale.getDefault()),
                        HttpStatus.CONFLICT
                );
            }
        } else {
            logger.warn("Tentative de suppression de la permission avec l'ID {} qui n'existe pas.", id);

            throw new EntityNotFoundException(messageSource.getMessage("permission.notFound", new Object[]{id},
                    Locale.getDefault()));
        }
    }

    @Override
    public Optional<Permission> rechercherParCode(String code) {
        return permissionRepository.findByCode(code);
    }

    @Override
    public Boolean existeParDescription(String description) {
        return permissionRepository.existsByDescription(description);
    }

    @Override
    public PermissionDto modifierUnePermission(PermissionDto permissionDto, Long id) {
        return permissionRepository.findById(id)
                .map(permission -> {
                    Permission permissionEntity = permissionRepository.save
                            (mapper.dtoToEntity(permissionDto, Permission.class));

                    return mapper.entityToDto(permissionEntity, PermissionDto.class);
                }).orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("permission non trouver",
                        new Object[]{id}, Locale.getDefault())));
    }

    @Override
    public List<Permission> findByCode(String code) {
        return permissionRepository.findPermissionByCode(code);
    }

    @Override
    public ResponseEntity<?> findByCodePage(int page, int size, String code) {
        if(code == null || code.isEmpty()){
            return ResponseEntity.badRequest().body(null);
        }

        Pageable pageable = PageRequest.of(page , size , Sort.by("id").descending());
        return  ResponseEntity.ok(permissionRepository.findPermissionByCodePage(pageable , code));
    }
}
