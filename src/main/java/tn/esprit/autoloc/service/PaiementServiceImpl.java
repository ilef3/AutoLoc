package tn.esprit.autoloc.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.repository.ContratRepository;
import tn.esprit.autoloc.repository.PaiementRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class PaiementServiceImpl implements IPaiementService {

    private final PaiementRepository paiementRepository;
    private final ContratRepository contratRepository;

    @Override
    public List<Paiement> retrieveAllPaiements() {
        return paiementRepository.findAll();
    }

    @Override
    public Paiement retrievePaiement(Long idPaiement) {
        return paiementRepository.findById(idPaiement)
                .orElseThrow(() -> new RuntimeException("Paiement introuvable avec l'identifiant : " + idPaiement));
    }

    @Override
    public Paiement addPaiement(Paiement paiement) {
        if (paiement.getDatePaiement() == null) {
            paiement.setDatePaiement(LocalDate.now());
        }
        return paiementRepository.save(paiement);
    }

    @Override
    public Paiement updatePaiement(Paiement paiement) {
        if (!paiementRepository.existsById(paiement.getIdPaiement())) {
            throw new RuntimeException("Impossible de mettre à jour : Paiement introuvable avec l'identifiant : " + paiement.getIdPaiement());
        }
        return paiementRepository.save(paiement);
    }

    @Override
    public void removePaiement(Long idPaiement) {
        if (!paiementRepository.existsById(idPaiement)) {
            throw new RuntimeException("Impossible de supprimer : Paiement introuvable avec l'identifiant : " + idPaiement);
        }
        paiementRepository.deleteById(idPaiement);
    }

    @Override
    @Transactional
    public Paiement ajouterPaiementAContrat(Long idContrat, Paiement paiement) {
        Contrat contrat = contratRepository.findById(idContrat)
                .orElseThrow(() -> new RuntimeException("Contrat introuvable avec l'identifiant : " + idContrat));

        paiement.setContrat(contrat);
        if (paiement.getDatePaiement() == null) {
            paiement.setDatePaiement(LocalDate.now());
        }

        return paiementRepository.save(paiement);
    }
}
