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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dioufserignemor.gmail.gestionelection.DTO.ResultatDTO;
import com.dioufserignemor.gmail.gestionelection.DTO.VoteDTO;
import com.dioufserignemor.gmail.gestionelection.entites.Vote;
import com.dioufserignemor.gmail.gestionelection.services.VoteService;

@RestController
@RequestMapping("/api/voters")
public class VoteController {

    @Autowired
    private VoteService voterService;

    @GetMapping
    public ResponseEntity<List<Vote>> getAllVoters() {
        List<Vote> voters = voterService.getAllVotes();
        return new ResponseEntity<>(voters, HttpStatus.OK);
    }

    @GetMapping("/{voterId}")
    public ResponseEntity<Vote> getVoterById(@PathVariable Long voterId) {
        Vote voter = voterService.getVoteById(voterId);
        return new ResponseEntity<>(voter, HttpStatus.OK);
    }

    
    @PostMapping("/vote")
    public ResponseEntity<Vote> voter(@RequestParam Long candidatId , @RequestParam Long electeurId) {
        Vote message = voterService.createVote(candidatId, electeurId);
        if (message != null) { 
            return ResponseEntity.status(HttpStatus.CREATED).body(message);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/results")
    public ResponseEntity<List<ResultatDTO>> getResults(Long id ) {
        List<ResultatDTO> results = voterService.getResults(id);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @PutMapping("/{voterId}")
    public ResponseEntity<Vote> updateVoter(@PathVariable Long voterId, @RequestBody VoteDTO voterDTO) {
        Vote message = voterService.updateVote(voterId, voterDTO);
        if (message != null) {
            return ResponseEntity.ok(message);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{voterId}")
    public ResponseEntity<?> deleteVoter(@PathVariable Long voterId) {
         voterService.deleteVote(voterId);
        return ResponseEntity.ok("vote suprimer avec succes");
    }
}
