package com.dioufserignemor.gmail.gestionelection.DTO;

public class ResultatDTO {

    private String candidatName;
    private Long votes;

    public ResultatDTO(String candidatName, Long votes) {
        this.candidatName = candidatName;
        this.votes = votes;
    }

    public String getCandidatName() {
        return candidatName;
    }

    public void setCandidatName(String candidatName) {
        this.candidatName = candidatName;
    }

    public Long getVotes() {
        return votes;
    }

    public void setVotes(Long votes) {
        this.votes = votes;
    }  
}
