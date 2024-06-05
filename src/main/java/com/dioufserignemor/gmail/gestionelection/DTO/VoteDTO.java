package com.dioufserignemor.gmail.gestionelection.DTO;

import com.dioufserignemor.gmail.gestionelection.entites.Candidat;
import com.dioufserignemor.gmail.gestionelection.entites.Electeur;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VoteDTO {
    private Long id ;
    private Long candidat ;
    private Long election ;
}
