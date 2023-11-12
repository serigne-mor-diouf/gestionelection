package com.dioufserignemor.gmail.gestionelection.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dioufserignemor.gmail.gestionelection.DTO.ElecteurDTO;
import com.dioufserignemor.gmail.gestionelection.entites.Electeur;
import com.dioufserignemor.gmail.gestionelection.services.ElecteurService;

@RestController
@RequestMapping("/api/electeurs")
public class ElecteurControlleur {
    
    @Autowired
    private ElecteurService  electeurService ;

     @PostMapping("/register")
    public ResponseEntity<Electeur> registerElecteur(@RequestBody ElecteurDTO electeurDTO) {
        Electeur electeur = electeurService.register(electeurDTO);
        if (electeur != null) { 
            return ResponseEntity.status(HttpStatus.CREATED).body(electeur);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

//modifier un electeur
    @PutMapping("/{electeurId}")
    public ResponseEntity<Electeur> updateElecteur( @PathVariable Long electeurId, @RequestBody ElecteurDTO electeurDTO ) {       
        Electeur response = electeurService.updateElecteur(electeurId, electeurDTO);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    //rechercher un electeur par id
     @GetMapping("/{electeurId}")
    public ResponseEntity<Electeur> getCandidateById(@PathVariable Long electeurId) {
        Electeur electeur= electeurService.getElecteurById(electeurId);
        return new ResponseEntity<>(electeur, HttpStatus.OK);
    }

    //lister des electeurs 
    @GetMapping
    public List<Electeur>getAllElecteurs() {
        return electeurService.getAllElecteurs();      
    }

    //supprimer

    @DeleteMapping("/{electeurId}")
    public ResponseEntity<Void> deleteElecteur(@PathVariable Long electeurId) {
        electeurService.deleteElecteur(electeurId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
