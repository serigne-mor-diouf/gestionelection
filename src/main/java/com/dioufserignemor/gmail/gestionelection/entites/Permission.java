package com.dioufserignemor.gmail.gestionelection.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.util.Collection;

@Data
@Getter
@Setter
@Entity
@Table(name = "permission_utilisateur", uniqueConstraints = {
        @UniqueConstraint(name = "code_unique", columnNames = { "code"}) })
@AllArgsConstructor
@NoArgsConstructor
public class Permission implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    @NotBlank
    @Column(name = "code")
    private String code;
    // private String code;

    @NotNull
    @NotEmpty
    @NotBlank
    @Lob
    @Column(unique = true)
    private String description;

    @ManyToMany(mappedBy = "permissions", fetch = FetchType.EAGER)
    @JsonIgnore
    private Collection<Role> roles;

    @PreRemove
    public void checkReviewAssociationBeforeRemoval() {
        if (!this.roles.isEmpty()) {
            throw new RuntimeException("Vous ne pouvez pas supprimer la permission car elle est utilisée par les rôles utilisateurs.");
        }
    }
}