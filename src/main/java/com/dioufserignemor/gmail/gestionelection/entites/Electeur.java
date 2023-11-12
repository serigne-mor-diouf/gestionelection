package com.dioufserignemor.gmail.gestionelection.entites;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Electeur implements Serializable  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String nationalId;
    private LocalDate dateOfBirth;
    private String username ; 
    private String password;
    @JsonIgnore
    @OneToMany(mappedBy = "electeur" , cascade = CascadeType.ALL)
    private List<Vote> votes;

    @ManyToMany
    @JoinTable(
        name = "electeur_roles",
        joinColumns = @JoinColumn(name = "electeur_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    public Electeur() {
    }

    public Electeur(String name, String nationalId, LocalDate dateOfBirth, String voterId, String password,
            List<Vote> votes) {
        this.name = name;
        this.nationalId = nationalId;
        this.dateOfBirth = dateOfBirth;
        this.username = voterId;
        this.password = password;
        this.votes = votes;
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

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String voterId) {
        this.username = voterId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

   
    
}
