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
}
