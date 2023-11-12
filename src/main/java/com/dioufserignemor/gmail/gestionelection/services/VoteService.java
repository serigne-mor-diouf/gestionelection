package com.dioufserignemor.gmail.gestionelection.services;

import java.util.List;

import com.dioufserignemor.gmail.gestionelection.DTO.ResultatDTO;
import com.dioufserignemor.gmail.gestionelection.DTO.VoteDTO;
import com.dioufserignemor.gmail.gestionelection.entites.Vote;

public interface VoteService {
    Vote createVote(Long electeur_id , Long candidat_id);
    Vote getVoteById(Long voteId);
    List<Vote> getAllVotes();
    Vote updateVote(Long voteId, VoteDTO voteDTO);
    void deleteVote(Long voteId);
    List<ResultatDTO>getResults(Long candidatId);
    
}

