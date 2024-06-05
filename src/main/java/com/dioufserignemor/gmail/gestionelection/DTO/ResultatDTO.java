package com.dioufserignemor.gmail.gestionelection.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResultatDTO {
    private String candidatName;

    private Long votes;
}
