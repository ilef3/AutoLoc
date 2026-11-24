package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.service.IVehiculeService;

import java.util.List;

@Tag(name = "Gestion des Véhicules")
@RestController
@AllArgsConstructor
@RequestMapping("/vehicule")
public class VehiculeRestController {

    IVehiculeService vehiculeService;

    // http://localhost:8081/vehicule/retrieve-all-vehicules
    @Operation(description = "Récupérer tous les véhicules")
    @GetMapping("/retrieve-all-vehicules")
    public List<Vehicule> getVehicules() {
        List<Vehicule> listVehicules = vehiculeService.retrieveAllVehicules();
        return listVehicules;
    }

    // http://localhost:8081/vehicule/retrieve-vehicule/8
    @Operation(description = "Récupérer un véhicule par ID")
    @GetMapping("/retrieve-vehicule/{vehicule-id}")
    public Vehicule retrieveVehicule(@PathVariable("vehicule-id") Long vehiculeId) {
        Vehicule vehicule = vehiculeService.retrieveVehicule(vehiculeId);
        return vehicule;
    }

    // http://localhost:8081/vehicule/add-vehicule
    @Operation(description = "Ajouter un véhicule")
    @PostMapping("/add-vehicule")
    public Vehicule addVehicule(@RequestBody Vehicule v) {
        Vehicule vehicule = vehiculeService.addVehicule(v);
        return vehicule;
    }

    // http://localhost:8081/vehicule/remove-vehicule/1
    @Operation(description = "Supprimer un véhicule")
    @DeleteMapping("/remove-vehicule/{vehicule-id}")
    public void removeVehicule(@PathVariable("vehicule-id") Long vehiculeId) {
        vehiculeService.removeVehicule(vehiculeId);
    }

    // http://localhost:8081/vehicule/modify-vehicule
    @Operation(description = "Modifier un véhicule")
    @PutMapping({"/modify-vehicule", "/update-vehicule"})
    public Vehicule modifyVehicule(@RequestBody Vehicule v) {
        Vehicule vehicule = vehiculeService.updateVehicule(v);
        return vehicule;
    }

    // http://localhost:8081/vehicule/affecter-vehicule/1/2
    @Operation(description = "Affecter un véhicule à une agence (Scénario : véhicule neuf rattaché à une agence)")
    @PutMapping({"/affecter-vehicule/{vehicule-id}/{agence-id}", "/affecter-vehicule-agence/{vehicule-id}/{agence-id}"})
    public Vehicule affecterVehicule(@PathVariable("vehicule-id") Long vehiculeId,
                                     @PathVariable("agence-id") Long agenceId) {
        return vehiculeService.affecterVehicule(vehiculeId, agenceId);
    }

    // http://localhost:8081/vehicule/affecter-equipement/1/2
    @Operation(description = "Affecter un équipement à un véhicule (Scénario : installation siège bébé)")
    @PutMapping("/affecter-equipement/{vehicule-id}/{equipement-id}")
    public Vehicule affecterEquipement(@PathVariable("vehicule-id") Long vehiculeId,
                                       @PathVariable("equipement-id") Long equipementId) {
        return vehiculeService.affecterEquipement(vehiculeId, equipementId);
    }

    // http://localhost:8081/vehicule/desaffecter-equipement/1/2
    @Operation(description = "Désaffecter un équipement d'un véhicule (Scénario : retrait siège bébé au retour)")
    @PutMapping("/desaffecter-equipement/{vehicule-id}/{equipement-id}")
    public Vehicule desaffecterEquipement(@PathVariable("vehicule-id") Long vehiculeId,
                                         @PathVariable("equipement-id") Long equipementId) {
        return vehiculeService.desaffecterEquipement(vehiculeId, equipementId);
    }
}
