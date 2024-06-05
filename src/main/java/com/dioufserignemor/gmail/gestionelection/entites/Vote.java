package com.dioufserignemor.gmail.gestionelection.entites;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
@Entity
public class Vote implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "candidat_id", referencedColumnName = "id")
    private Candidat candidat;

    @ManyToOne
    @JoinColumn(name = "electeur_id", referencedColumnName = "id")
    private Electeur electeur;

    @ManyToOne
    @JoinColumn(name = "election_id", referencedColumnName = "id")
    private Election election;

    @Column(nullable = false)
    private LocalDateTime dateVote;

}
