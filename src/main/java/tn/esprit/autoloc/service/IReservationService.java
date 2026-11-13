package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.domain.StatutReservation;
import java.util.List;

public interface IReservationService {
    List<Reservation> retrieveAllReservations();
    Reservation retrieveReservation(Long idReservation);
    Reservation addReservation(Reservation reservation);
    Reservation updateReservation(Reservation reservation);
    void removeReservation(Long idReservation);
    Reservation creerReservation(Long idClient, Long idVehicule, Reservation reservation);
    Reservation changerStatutReservation(Long idReservation, StatutReservation statut);
}
