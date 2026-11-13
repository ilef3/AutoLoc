package tn.esprit.autoloc.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.domain.StatutReservation;
import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.ClientRepository;
import tn.esprit.autoloc.repository.ReservationRepository;
import tn.esprit.autoloc.repository.VehiculeRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ReservationServiceImpl implements IReservationService {

    private final ReservationRepository reservationRepository;
    private final ClientRepository clientRepository;
    private final VehiculeRepository vehiculeRepository;

    @Override
    public List<Reservation> retrieveAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation retrieveReservation(Long idReservation) {
        return reservationRepository.findById(idReservation)
                .orElseThrow(() -> new RuntimeException("Réservation introuvable avec l'identifiant : " + idReservation));
    }

    @Override
    public Reservation addReservation(Reservation reservation) {
        if (reservation.getStatut() == null) {
            reservation.setStatut(StatutReservation.EN_ATTENTE);
        }
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation updateReservation(Reservation reservation) {
        if (!reservationRepository.existsById(reservation.getIdReservation())) {
            throw new RuntimeException("Impossible de mettre à jour : Réservation introuvable avec l'identifiant : " + reservation.getIdReservation());
        }
        return reservationRepository.save(reservation);
    }

    @Override
    public void removeReservation(Long idReservation) {
        if (!reservationRepository.existsById(idReservation)) {
            throw new RuntimeException("Impossible de supprimer : Réservation introuvable avec l'identifiant : " + idReservation);
        }
        reservationRepository.deleteById(idReservation);
    }

    @Override
    @Transactional
    public Reservation creerReservation(Long idClient, Long idVehicule, Reservation reservation) {
        Client client = clientRepository.findById(idClient)
                .orElseThrow(() -> new RuntimeException("Client introuvable avec l'identifiant : " + idClient));
        Vehicule vehicule = vehiculeRepository.findById(idVehicule)
                .orElseThrow(() -> new RuntimeException("Véhicule introuvable avec l'identifiant : " + idVehicule));

        reservation.setClient(client);
        reservation.setVehicule(vehicule);
        if (reservation.getStatut() == null) {
            reservation.setStatut(StatutReservation.EN_ATTENTE);
        }

        return reservationRepository.save(reservation);
    }

    @Override
    @Transactional
    public Reservation changerStatutReservation(Long idReservation, StatutReservation statut) {
        Reservation reservation = retrieveReservation(idReservation);
        reservation.setStatut(statut);

        if (statut == StatutReservation.CONFIRMEE && reservation.getVehicule() != null) {
            reservation.getVehicule().setStatut(StatutVehicule.LOUE);
            vehiculeRepository.save(reservation.getVehicule());
        } else if ((statut == StatutReservation.ANNULEE || statut == StatutReservation.TERMINEE) && reservation.getVehicule() != null) {
            reservation.getVehicule().setStatut(StatutVehicule.DISPONIBLE);
            vehiculeRepository.save(reservation.getVehicule());
        }

        return reservationRepository.save(reservation);
    }
}
