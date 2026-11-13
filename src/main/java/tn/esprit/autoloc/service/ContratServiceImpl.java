package tn.esprit.autoloc.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.ContratRepository;
import tn.esprit.autoloc.repository.ReservationRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ContratServiceImpl implements IContratService {

    private final ContratRepository contratRepository;
    private final ReservationRepository reservationRepository;

    @Override
    public List<Contrat> retrieveAllContrats() {
        return contratRepository.findAll();
    }

    @Override
    public Contrat retrieveContrat(Long idContrat) {
        return contratRepository.findById(idContrat)
                .orElseThrow(() -> new RuntimeException("Contrat introuvable avec l'identifiant : " + idContrat));
    }

    @Override
    public Contrat addContrat(Contrat contrat) {
        return contratRepository.save(contrat);
    }

    @Override
    public Contrat updateContrat(Contrat contrat) {
        if (!contratRepository.existsById(contrat.getIdContrat())) {
            throw new RuntimeException("Impossible de mettre à jour : Contrat introuvable avec l'identifiant : " + contrat.getIdContrat());
        }
        return contratRepository.save(contrat);
    }

    @Override
    public void removeContrat(Long idContrat) {
        if (!contratRepository.existsById(idContrat)) {
            throw new RuntimeException("Impossible de supprimer : Contrat introuvable avec l'identifiant : " + idContrat);
        }
        contratRepository.deleteById(idContrat);
    }

    @Override
    @Transactional
    public Contrat creerContratPourReservation(Long idReservation) {
        Reservation reservation = reservationRepository.findById(idReservation)
                .orElseThrow(() -> new RuntimeException("Réservation introuvable avec l'identifiant : " + idReservation));

        Vehicule vehicule = reservation.getVehicule();
        long jours = 1;
        if (reservation.getDateDebut() != null && reservation.getDateFin() != null) {
            jours = ChronoUnit.DAYS.between(reservation.getDateDebut(), reservation.getDateFin());
            if (jours <= 0) {
                jours = 1;
            }
        }

        BigDecimal tarifJournalier = (vehicule != null && vehicule.getTarifJournalier() != null)
                ? vehicule.getTarifJournalier()
                : BigDecimal.ZERO;

        BigDecimal montantTotal = tarifJournalier.multiply(BigDecimal.valueOf(jours));

        Contrat contrat = new Contrat();
        contrat.setDateSignature(LocalDate.now());
        contrat.setMontantTotal(montantTotal);
        contrat.setValide(true);
        contrat.setReservation(reservation);

        return contratRepository.save(contrat);
    }

    @Override
    @Transactional
    public Contrat validerContrat(Long idContrat) {
        Contrat contrat = retrieveContrat(idContrat);
        contrat.setValide(true);
        return contratRepository.save(contrat);
    }
}
