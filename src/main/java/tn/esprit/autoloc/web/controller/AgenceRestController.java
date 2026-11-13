package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.service.IAgenceService;

import java.util.List;

@Tag(name = "Gestion des Agences")
@RestController
@AllArgsConstructor
@RequestMapping("/agence")
public class AgenceRestController {

    IAgenceService agenceService;

    // http://localhost:8081/agence/retrieve-all-agences
    @Operation(description = "Récupérer toutes les agences")
    @GetMapping("/retrieve-all-agences")
    public List<Agence> getAgences() {
        return agenceService.retrieveAllAgences();
    }

    // http://localhost:8081/agence/retrieve-agence/1
    @Operation(description = "Récupérer une agence par ID")
    @GetMapping("/retrieve-agence/{agence-id}")
    public Agence retrieveAgence(@PathVariable("agence-id") Long agenceId) {
        return agenceService.retrieveAgence(agenceId);
    }

    // http://localhost:8081/agence/add-agence
    @Operation(description = "Ajouter une agence")
    @PostMapping("/add-agence")
    public Agence addAgence(@RequestBody Agence a) {
        return agenceService.addAgence(a);
    }

    // http://localhost:8081/agence/modify-agence
    @Operation(description = "Modifier une agence")
    @PutMapping("/modify-agence")
    public Agence modifyAgence(@RequestBody Agence a) {
        return agenceService.updateAgence(a);
    }

    // http://localhost:8081/agence/remove-agence/1
    @Operation(description = "Supprimer une agence")
    @DeleteMapping("/remove-agence/{agence-id}")
    public void removeAgence(@PathVariable("agence-id") Long agenceId) {
        agenceService.removeAgence(agenceId);
    }
}

