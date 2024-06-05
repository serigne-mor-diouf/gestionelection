package com.dioufserignemor.gmail.gestionelection.entites;


import java.util.Collection;
import java.util.List;

import com.dioufserignemor.gmail.gestionelection.entites.base.Base;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nom")
    private String nom;

    @ManyToMany(mappedBy = "role", fetch = FetchType.EAGER)
    @JsonIgnore
    private Collection<Utilisateur> utilisateurs;

    @JsonIgnoreProperties(value = {"role"})
    @ManyToMany()
    @JoinTable(
            name = "role_permission",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private Collection<Permission> permissions;

    public Role(String nom) {
        this.nom = nom;
    }

    @PreRemove
    public void checkReviewAssociationBeforeRemoval() {
        if (!this.utilisateurs.isEmpty()) {
            throw new RuntimeException("Vous ne pouvez pas supprimer le rôle [ groupe d'utilisateur car il regroupe des utilisateurs.");
        }
    }

}
