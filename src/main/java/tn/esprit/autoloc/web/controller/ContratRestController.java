package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.service.IContratService;

import java.util.List;

@Tag(name = "Gestion des Contrats")
@RestController
@AllArgsConstructor
@RequestMapping("/contrat")
public class ContratRestController {

    IContratService contratService;

    // http://localhost:8081/contrat/retrieve-all-contrats
    @Operation(description = "Récupérer tous les contrats")
    @GetMapping("/retrieve-all-contrats")
    public List<Contrat> getContrats() {
        return contratService.retrieveAllContrats();
    }

    // http://localhost:8081/contrat/retrieve-contrat/1
    @Operation(description = "Récupérer un contrat par ID")
    @GetMapping("/retrieve-contrat/{contrat-id}")
    public Contrat retrieveContrat(@PathVariable("contrat-id") Long contratId) {
        return contratService.retrieveContrat(contratId);
    }

    // http://localhost:8081/contrat/add-contrat
    @Operation(description = "Ajouter un contrat")
    @PostMapping("/add-contrat")
    public Contrat addContrat(@RequestBody Contrat c) {
        return contratService.addContrat(c);
    }

    // http://localhost:8081/contrat/modify-contrat
    @Operation(description = "Modifier un contrat")
    @PutMapping("/modify-contrat")
    public Contrat modifyContrat(@RequestBody Contrat c) {
        return contratService.updateContrat(c);
    }

    // http://localhost:8081/contrat/remove-contrat/1
    @Operation(description = "Supprimer un contrat")
    @DeleteMapping("/remove-contrat/{contrat-id}")
    public void removeContrat(@PathVariable("contrat-id") Long contratId) {
        contratService.removeContrat(contratId);
    }

    // http://localhost:8081/contrat/creer-contrat-pour-reservation/1
    @Operation(description = "Créer un contrat pour une réservation existante")
    @PostMapping("/creer-contrat-pour-reservation/{reservation-id}")
    public Contrat creerContratPourReservation(@PathVariable("reservation-id") Long reservationId) {
        return contratService.creerContratPourReservation(reservationId);
    }

    // http://localhost:8081/contrat/valider-contrat/1
    @Operation(description = "Valider un contrat")
    @PutMapping("/valider-contrat/{contrat-id}")
    public Contrat validerContrat(@PathVariable("contrat-id") Long contratId) {
        return contratService.validerContrat(contratId);
    }
}

