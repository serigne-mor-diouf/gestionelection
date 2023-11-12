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

import com.dioufserignemor.gmail.gestionelection.DTO.CandidatDTO;
import com.dioufserignemor.gmail.gestionelection.entites.Candidat;
import com.dioufserignemor.gmail.gestionelection.services.CandidatService;

@RestController
@RequestMapping("/api/voting")
public class CandidatController {
     @Autowired
    private CandidatService candidatService;

    @GetMapping("/candidates")
    public ResponseEntity<List<Candidat>> getAllCandidates() {
        List<Candidat> candidates = candidatService.getAllCandidats();
        return new ResponseEntity<>(candidates, HttpStatus.OK);
    }

    @GetMapping("/{candidatId}")
    public ResponseEntity<Candidat> getCandidateById(@PathVariable Long candidatId) {
        Candidat candidate = candidatService.getCandidatById(candidatId);
        return new ResponseEntity<>(candidate, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Candidat> createCandidate(@RequestBody CandidatDTO candidatDTO) {
        Candidat createdCandidate = candidatService.createCandidat(candidatDTO);
        if (createdCandidate != null) { 
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCandidate);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{candidatId}")
    public ResponseEntity<Candidat> updateCandidate(@PathVariable Long candidatId , @RequestBody CandidatDTO candidatDTO) {
        Candidat updatedCandidate = candidatService.updateCandidat(candidatId, candidatDTO);
        if (updatedCandidate != null) {
            return ResponseEntity.ok(updatedCandidate);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{candidatId}")
    public ResponseEntity<Void> deleteCandidate(@PathVariable Long candidatId) {
        candidatService.deleteCandidat(candidatId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
