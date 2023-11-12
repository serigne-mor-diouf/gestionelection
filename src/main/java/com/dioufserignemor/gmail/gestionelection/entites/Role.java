package com.dioufserignemor.gmail.gestionelection.entites;

import java.util.List;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // par exemple, "ROLE_ELECTOR" ou "ROLE_ADMIN"

    @ManyToMany(mappedBy = "roles")
    private List<Electeur> electeurs;

    //
    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Electeur> getElecteurs() {
        return electeurs;
    }

    public void setElecteurs(List<Electeur> electeurs) {
        this.electeurs = electeurs;
    }

    
}
