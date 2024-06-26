package com.dioufserignemor.gmail.gestionelection.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CandidatDTO {
    private Long id;
    private String name;
    private String party ;
    private Long utilisateur ;
}
