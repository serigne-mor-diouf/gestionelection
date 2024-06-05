package com.dioufserignemor.gmail.gestionelection.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UtilisateurDto {
    private String email;

    private String password ;

    private boolean estAdmin = false;

    private boolean estActif = false;

    private LocalDateTime dernierConnexion;

    private String codeImageDeProfil;

    private long tailleImageDeProfil;

    @JsonProperty("role")
    private Set<Long> role ;

    @JsonProperty("electeur")
    private Long electeur;
}
