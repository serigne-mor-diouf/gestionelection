package com.dioufserignemor.gmail.gestionelection.implementations;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.dioufserignemor.gmail.gestionelection.DTO.ResultatDTO;
import com.dioufserignemor.gmail.gestionelection.DTO.VoteDTO;
import com.dioufserignemor.gmail.gestionelection.entites.Candidat;
import com.dioufserignemor.gmail.gestionelection.entites.Electeur;
import com.dioufserignemor.gmail.gestionelection.entites.Vote;
import com.dioufserignemor.gmail.gestionelection.repositories.CandidatRepository;
import com.dioufserignemor.gmail.gestionelection.repositories.ElecteurRepository;
import com.dioufserignemor.gmail.gestionelection.repositories.VoteRepository;
import com.dioufserignemor.gmail.gestionelection.services.VoteService;

@Service
public class VoteServiceImpl implements VoteService {
@Autowired
private VoteRepository voteRepository ;
@Autowired
private ElecteurRepository electeurRepository ;

@Autowired
private CandidatRepository candidatRepository  ;

    @Override
    public Vote createVote(Long electeurId, Long candidatId) {
        Electeur electeur = electeurRepository.findById(electeurId)
                .orElseThrow(() -> new RuntimeException("Électeur non trouvé pour l'ID : " + electeurId));


        // Rechercher le candidat par son ID
        Candidat candidat = candidatRepository.findById(candidatId)
                .orElseThrow(() -> new RuntimeException("Candidat non trouvé pour l'ID : " + candidatId));

        Vote vote = new Vote();

        //vote.setElecteur(electeur);
        vote.setCandidat(candidat);

        return voteRepository.save(vote);
    }


    @Override
    public Vote getVoteById(Long voteId) {
    return voteRepository.findById(voteId).orElse(null) ;
    }
//lister des votes 
    @Override
    public List<Vote> getAllVotes() {
       return voteRepository.findAll() ;
    }

    @Override
    public Vote updateVote(Long voteId, VoteDTO voteDTO) {
        // Récupérer le vote existant
        Vote existeVote = voteRepository.findById(voteId)
                .orElseThrow(() -> new RuntimeException("Vote non trouvé pour l'ID : " + voteId));
        // Mise à jour de l'électeur si le DTO contient un nouvel électeur

        // Mise à jour du candidat si le DTO contient un nouveau candidat
        if (voteDTO.getCandidat() != null) {
            Candidat newCandidat = candidatRepository.findById(voteDTO.getId())
                    .orElseThrow(() -> new RuntimeException("Candidat non trouvé pour l'ID : " + voteDTO.getCandidat()));
            existeVote.setCandidat(newCandidat);
        }

        return voteRepository.save(existeVote);
    }

    @Override
   
    public void deleteVote(Long voteId) {
        Vote voteToDelete = voteRepository.findById(voteId)
            .orElseThrow(() -> new RuntimeException("Vote non trouvé pour l'ID : " + voteId));

        voteRepository.delete(voteToDelete);
    }

     @Scheduled(fixedRate = 60000) // exécuter toutes les minutes
    public void displayTopCandidates() {
        List<ResultatDTO> topCandidates = troisVainqueurCandidates();

        // Afficher les trois premiers candidats avec leurs voix sur la console
        for (ResultatDTO candidat : topCandidates) {
            System.out.println("Candidat: " + candidat.getCandidatName() + ", Voix: " + candidat.getVotes());
        }
    }

    private List<ResultatDTO> troisVainqueurCandidates() {
        // Récupérer les trois premiers candidats avec le nombre de voix
        List<Object[]> result = voteRepository.vainqueureTopCandidates();

        // Mapper les résultats en objets ResultatDTO
        List<ResultatDTO> topCandidates = result.stream()
                .map(row -> new ResultatDTO((String) row[0], (Long) row[1]))
                .collect(Collectors.toList());

        return topCandidates;
    }


    @Override
    public List<ResultatDTO> getResults(Long candidatId) {
         // Vérifier si l'appelant est un candidat
        boolean isCandidat = candidatRepository.existsById(candidatId);

        if (isCandidat) {
            return troisVainqueurCandidates();
        } else {
            throw new RuntimeException("Accès refusé. Seuls les candidats sont autorisés.");
        }
    }


    
}
