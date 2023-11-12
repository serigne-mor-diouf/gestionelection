package com.dioufserignemor.gmail.gestionelection.DTO;

import java.time.LocalDate;
public class ElecteurDTO {
     private Long id;
     private String name;
     private String nationalId;
     private LocalDate dateOfBirth;
     private String username; 
     private String password;

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

   public void setUsername(String username) {
       this.username = username;
   }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }



}
