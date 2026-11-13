package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.service.IPaiementService;

import java.util.List;

@Tag(name = "Gestion des Paiements")
@RestController
@AllArgsConstructor
@RequestMapping("/paiement")
public class PaiementRestController {

    IPaiementService paiementService;

    // http://localhost:8081/paiement/retrieve-all-paiements
    @Operation(description = "Récupérer tous les paiements")
    @GetMapping("/retrieve-all-paiements")
    public List<Paiement> getPaiements() {
        return paiementService.retrieveAllPaiements();
    }

    // http://localhost:8081/paiement/retrieve-paiement/1
    @Operation(description = "Récupérer un paiement par ID")
    @GetMapping("/retrieve-paiement/{paiement-id}")
    public Paiement retrievePaiement(@PathVariable("paiement-id") Long paiementId) {
        return paiementService.retrievePaiement(paiementId);
    }

    // http://localhost:8081/paiement/add-paiement
    @Operation(description = "Ajouter un paiement")
    @PostMapping("/add-paiement")
    public Paiement addPaiement(@RequestBody Paiement p) {
        return paiementService.addPaiement(p);
    }

    // http://localhost:8081/paiement/modify-paiement
    @Operation(description = "Modifier un paiement")
    @PutMapping("/modify-paiement")
    public Paiement modifyPaiement(@RequestBody Paiement p) {
        return paiementService.updatePaiement(p);
    }

    // http://localhost:8081/paiement/remove-paiement/1
    @Operation(description = "Supprimer un paiement")
    @DeleteMapping("/remove-paiement/{paiement-id}")
    public void removePaiement(@PathVariable("paiement-id") Long paiementId) {
        paiementService.removePaiement(paiementId);
    }

    // http://localhost:8081/paiement/ajouter-paiement-contrat/1
    @Operation(description = "Ajouter un paiement à un contrat")
    @PostMapping("/ajouter-paiement-contrat/{contrat-id}")
    public Paiement ajouterPaiementAContrat(@PathVariable("contrat-id") Long contratId,
                                             @RequestBody Paiement p) {
        return paiementService.ajouterPaiementAContrat(contratId, p);
    }
}

