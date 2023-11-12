package com.dioufserignemor.gmail.gestionelection.DTO;

import com.dioufserignemor.gmail.gestionelection.entites.Candidat;
import com.dioufserignemor.gmail.gestionelection.entites.Electeur;

public class VoteDTO {
    private Long id ;
    private Candidat candidat ;
    private Electeur  electeur ;

    public Candidat getCandidat() {
        return candidat;
    }

    public void setCandidat(Candidat candidat) {
        this.candidat = candidat;
    }

    public Electeur getElecteur() {
        return electeur;
    }

    public void setElecteur(Electeur electeur) {
        this.electeur = electeur;
    }
  public Long getId() {
      return id;
  }

  public void setId(Long id) {
      this.id = id;
  }

}
