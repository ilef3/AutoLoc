package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.service.IEquipementService;

import java.util.List;

@Tag(name = "Gestion des Équipements")
@RestController
@AllArgsConstructor
@RequestMapping("/equipement")
public class EquipementRestController {

    IEquipementService equipementService;

    // http://localhost:8081/equipement/retrieve-all-equipements
    @Operation(description = "Récupérer tous les équipements")
    @GetMapping("/retrieve-all-equipements")
    public List<Equipement> getEquipements() {
        return equipementService.retrieveAllEquipements();
    }

    // http://localhost:8081/equipement/retrieve-equipement/1
    @Operation(description = "Récupérer un équipement par ID")
    @GetMapping("/retrieve-equipement/{equipement-id}")
    public Equipement retrieveEquipement(@PathVariable("equipement-id") Long equipementId) {
        return equipementService.retrieveEquipement(equipementId);
    }

    // http://localhost:8081/equipement/add-equipement
    @Operation(description = "Ajouter un équipement")
    @PostMapping("/add-equipement")
    public Equipement addEquipement(@RequestBody Equipement e) {
        return equipementService.addEquipement(e);
    }

    // http://localhost:8081/equipement/modify-equipement
    @Operation(description = "Modifier un équipement")
    @PutMapping("/modify-equipement")
    public Equipement modifyEquipement(@RequestBody Equipement e) {
        return equipementService.updateEquipement(e);
    }

    // http://localhost:8081/equipement/remove-equipement/1
    @Operation(description = "Supprimer un équipement")
    @DeleteMapping("/remove-equipement/{equipement-id}")
    public void removeEquipement(@PathVariable("equipement-id") Long equipementId) {
        equipementService.removeEquipement(equipementId);
    }
}

