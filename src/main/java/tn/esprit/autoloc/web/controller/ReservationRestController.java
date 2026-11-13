package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.domain.StatutReservation;
import tn.esprit.autoloc.service.IReservationService;

import java.util.List;

@Tag(name = "Gestion des Réservations")
@RestController
@AllArgsConstructor
@RequestMapping("/reservation")
public class ReservationRestController {

    IReservationService reservationService;

    // http://localhost:8081/reservation/retrieve-all-reservations
    @Operation(description = "Récupérer toutes les réservations")
    @GetMapping("/retrieve-all-reservations")
    public List<Reservation> getReservations() {
        return reservationService.retrieveAllReservations();
    }

    // http://localhost:8081/reservation/retrieve-reservation/1
    @Operation(description = "Récupérer une réservation par ID")
    @GetMapping("/retrieve-reservation/{reservation-id}")
    public Reservation retrieveReservation(@PathVariable("reservation-id") Long reservationId) {
        return reservationService.retrieveReservation(reservationId);
    }

    // http://localhost:8081/reservation/add-reservation
    @Operation(description = "Ajouter une réservation")
    @PostMapping("/add-reservation")
    public Reservation addReservation(@RequestBody Reservation r) {
        return reservationService.addReservation(r);
    }

    // http://localhost:8081/reservation/modify-reservation
    @Operation(description = "Modifier une réservation")
    @PutMapping("/modify-reservation")
    public Reservation modifyReservation(@RequestBody Reservation r) {
        return reservationService.updateReservation(r);
    }

    // http://localhost:8081/reservation/remove-reservation/1
    @Operation(description = "Supprimer une réservation")
    @DeleteMapping("/remove-reservation/{reservation-id}")
    public void removeReservation(@PathVariable("reservation-id") Long reservationId) {
        reservationService.removeReservation(reservationId);
    }

    // http://localhost:8081/reservation/creer-reservation/1/2
    @Operation(description = "Créer une réservation pour un client et un véhicule")
    @PostMapping("/creer-reservation/{client-id}/{vehicule-id}")
    public Reservation creerReservation(@PathVariable("client-id") Long clientId,
                                         @PathVariable("vehicule-id") Long vehiculeId,
                                         @RequestBody Reservation r) {
        return reservationService.creerReservation(clientId, vehiculeId, r);
    }

    // http://localhost:8081/reservation/changer-statut/1?statut=CONFIRMEE
    @Operation(description = "Changer le statut d'une réservation")
    @PutMapping("/changer-statut/{reservation-id}")
    public Reservation changerStatut(@PathVariable("reservation-id") Long reservationId,
                                      @RequestParam StatutReservation statut) {
        return reservationService.changerStatutReservation(reservationId, statut);
    }
}

