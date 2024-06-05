package com.dioufserignemor.gmail.gestionelection.implementations;

import com.dioufserignemor.gmail.gestionelection.DTO.UtilisateurDto;
import com.dioufserignemor.gmail.gestionelection.entites.Role;
import com.dioufserignemor.gmail.gestionelection.entites.Utilisateur;
import com.dioufserignemor.gmail.gestionelection.exception.EntityNotFoundException;
import com.dioufserignemor.gmail.gestionelection.exception.RequestException;
import com.dioufserignemor.gmail.gestionelection.repositories.RoleRepository;
import com.dioufserignemor.gmail.gestionelection.repositories.UtilisateurRepository;
import com.dioufserignemor.gmail.gestionelection.services.UtilisateurService;
import lombok.AllArgsConstructor;
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

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private RoleRepository roleRepository;

    GenericMapper<Utilisateur, UtilisateurDto> mapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    MessageSource messageSource;

    private static final Logger logger = LogManager.getLogger(UtilisateurService.class);

    @Override
    public Collection<UtilisateurDto> afficherLesUtilisateurs() {

        return utilisateurRepository.findAll()
                .stream()
                .map(utilisateur -> mapper.entityToDto(utilisateur, UtilisateurDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Utilisateur> obtenirUnUtilisateurParEmail(String email) {
        return utilisateurRepository.findByEmail(email);
    }

    @Override
    public UtilisateurDto ajouterUnUtilisateur(UtilisateurDto inscriptionDto) {
        Utilisateur utilisateurEntity = mapper.dtoToEntity(inscriptionDto, Utilisateur.class);
        Utilisateur utilisateur = utilisateurRepository.save(utilisateurEntity);
        return mapper.entityToDto(utilisateur, UtilisateurDto.class);
    }

    @Override
    public boolean existsByEmail(String email) {
        return utilisateurRepository.existsByEmail(email);
    }

    @Override
    public Utilisateur creerUnMotDePasse(String motDePasse, Utilisateur utilisateur) {
        utilisateur.setPassword(passwordEncoder.encode(motDePasse));
        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public Collection<Long> listeDesElecteursUtilisateur() {
        return utilisateurRepository.listeDesElecteurtilisateur();
    }

    @Override
    public Boolean existeParId(Long id) {
        return utilisateurRepository.existsById(id);
    }

    @Override
    public Boolean existeParPersonnelId(Long id) {
        return utilisateurRepository.existsByElecteurId(id);
    }

    @Override
    public Optional<Utilisateur> rechercherParId(Long id) {
        return utilisateurRepository.findById(id);
    }

    @Override
    public void supprimerUnUtilisateurParId(Long id) {
        if (utilisateurRepository.findById(id).isPresent()) {
            try {
                utilisateurRepository.deleteById(id);
                logger.info("L'utilisateur avec l'ID {} a été supprimé.", id);

            } catch (Exception exception) {
                logger.error("Erreur lors de la suppression de l'utilisateur avec l'ID {} : {}", id, exception.getMessage());

                throw new RequestException(
                        messageSource.getMessage("utilisateur.errorDeletion", new Object[]{id},
                                Locale.getDefault()),
                        HttpStatus.CONFLICT
                );
            }
        } else {
            logger.warn("Tentative de suppression de l'utilisateur avec l'ID {} qui n'existe pas.", id);

            throw new EntityNotFoundException(messageSource.getMessage("utilisateur.notFound", new Object[]{id},
                    Locale.getDefault()));
        }
    }

    @Override
    public Collection<Role> afficherLesRolesDeLutilisateur(Long id) {
        return utilisateurRepository.findById(id).get().getRole();
    }

//    @Override
//    public UtilisateurDto modifierUnUtilisateur(UtilisateurDto utilisateurDto, Long id) {
//       return utilisateurRepository.findById(id).map(utilisateur -> {
//           Utilisateur utilisateurEntity = utilisateurRepository.save(
//                   mapper.dtoToEntity(utilisateurDto, Utilisateur.class)
//               );
//
//           Set<Role>roles = new HashSet<>();
//           for (Long roleId : utilisateurDto.getRole()) {
//               Role role= roleRepository.getById(roleId);
//               roles.add(role);
//           }
//           utilisateurEntity.getRole().removeAll(utilisateurEntity.getRole());
//           utilisateurEntity.getRole().addAll(roles);
//
//            return mapper.entityToDto(utilisateurEntity, UtilisateurDto.class);
//    }).orElseThrow(()->new EntityNotFoundException(messageSource.getMessage("utilisateur non trouve",
//               new Object[]{id},Locale.getDefault())));
//
//    }
@Override
public UtilisateurDto modifierUnUtilisateur(UtilisateurDto utilisateurDto, Long id) {
    return utilisateurRepository.findById(id).map(utilisateur -> {
        // Utilisez le mapper pour mapper les champs du DTO vers l'entité utilisateur existante
        Utilisateur utilisateurMiseAJour = mapper.dtoToEntity(utilisateurDto, Utilisateur.class);

        // Met à jour les rôles
        Set<Role> roles = utilisateurDto.getRole().stream()
                .map(roleId -> roleRepository.findById(roleId)
                        .orElseThrow(() -> new EntityNotFoundException(
                                messageSource.getMessage("role non trouvé", new Object[]{roleId}, Locale.getDefault()))))
                .collect(Collectors.toSet());

        utilisateurMiseAJour.setRole(roles);
        // Sauvegarde l'utilisateur mis à jour
        utilisateurMiseAJour = utilisateurRepository.save(utilisateurMiseAJour);

        // Retourne l'utilisateur mis à jour en tant que DTO
        return mapper.entityToDto(utilisateurMiseAJour, UtilisateurDto.class);
    }).orElseThrow(() -> new EntityNotFoundException(
            messageSource.getMessage("utilisateur non trouvé", new Object[]{id}, Locale.getDefault())));
}

    @Override
    public Utilisateur inverserEtatUtilisateur(Long utilisateurId) {
        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new IllegalArgumentException("utilisateur introuvable"));

        //inverser l'etat de l'utilisateur
        utilisateur.setEstActif(!utilisateur.isEstActif());

        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public Utilisateur inverserAdminUtilisateur(Long utilisateurId) {
        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new IllegalArgumentException("utilisateur introuvable"));

        //changer le role de l'utilisateur
        utilisateur.setEstAdmin(!utilisateur.isEstAdmin());

        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public Utilisateur ajouterRoleAUtilisateur(Long utilisateurId, Collection<Long> listeIdRole) {
        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId)
                .orElseThrow((() -> new IllegalArgumentException("utilisateur introuvable")));

        Collection<Role> nouveauxRole = roleRepository.findAllById(listeIdRole);
        List<Role> roleAnciens = new ArrayList<>(utilisateur.getRole());
        nouveauxRole.removeIf(roleAnciens::contains);

        if (nouveauxRole.isEmpty()) {
            return utilisateur;
        }
        utilisateur.getRole().addAll(nouveauxRole);
        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public Utilisateur retirerRoleAUtilisateur(Long utilisateurId, Collection<Long> listeIdRole) {
        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new IllegalArgumentException("utilisateur introuvable"));

        //Vérifier si les roles à retirer existent dans la liste des roles de l'utilisateur
        Collection<Role> roleRetirer = roleRepository.findAllById(listeIdRole);
        List<Role> roleUtilisateur = new ArrayList<>(utilisateur.getRole());
        boolean roleTrouve = new HashSet<>(roleUtilisateur).containsAll(roleRetirer);

        // Si certaines des roles à retirer n'existent pas dans l'utilisateur, lever une exception ou gérer l'erreur selon votre besoin
        if (!roleTrouve) {
            logger.warn("certains roles specifiers ne corresponds pas aux roles de l'utilisateur");
            throw new IllegalArgumentException("Certains des rôles spécifiés ne correspondent pas aux rôles de l'utilisateur.");

        }

        // Retirer les roles spécifiées de la collection existante de l'utilisateur
        utilisateur.getRole().removeAll(roleRetirer);

        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public ResponseEntity<?> findByNomPrenomPage(int page, int size, String designation) {
        if (designation == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Pageable pageable = PageRequest.of(page, size,
                Sort.by("id").descending());
        return ResponseEntity.ok(utilisateurRepository.findByElecteurNomAndPrenom(pageable, designation));
    }
}
