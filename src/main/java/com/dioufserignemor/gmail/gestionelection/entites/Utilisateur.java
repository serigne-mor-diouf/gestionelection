package com.dioufserignemor.gmail.gestionelection.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "utilisateur" ,  uniqueConstraints = {@UniqueConstraint( name = "email_unique",columnNames = {"email"})})
public class Utilisateur implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email ;

    @JsonIgnore
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", message = "Le mot de passe n'est pas valide. Minimum huit caractères, au moins une lettre majuscule, une lettre")
    private String password ;

    @ColumnDefault("true")
    @JsonProperty("est_actif")
    private boolean estActif = true;

    @ColumnDefault("false")
    private boolean estAdmin = false;

    //@JsonIgnore
    private LocalDateTime dernierConnexion;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "electeur_id", referencedColumnName = "id")
    private Electeur electeur;

    // @Column(name = "image_de_profil", unique = false, nullable = true, length =
    // 100000)
    @JsonProperty("image_de_profil")
    // private String imageDeProfil;

    private String codeImageDeProfil;

    private long tailleImageDeProfil;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "utilisateur_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Collection<Role> role;

    @OneToOne(mappedBy = "utilisateur")
    @JsonIgnore
    private Candidat candidat;
}
