package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.service.IMaintenanceService;

import java.util.List;

@Tag(name = "Gestion des Maintenances")
@RestController
@AllArgsConstructor
@RequestMapping("/maintenance")
public class MaintenanceRestController {

    IMaintenanceService maintenanceService;

    // http://localhost:8081/maintenance/retrieve-all-maintenances
    @Operation(description = "Récupérer toutes les maintenances")
    @GetMapping("/retrieve-all-maintenances")
    public List<Maintenance> getMaintenances() {
        return maintenanceService.retrieveAllMaintenances();
    }

    // http://localhost:8081/maintenance/retrieve-maintenance/1
    @Operation(description = "Récupérer une maintenance par ID")
    @GetMapping("/retrieve-maintenance/{maintenance-id}")
    public Maintenance retrieveMaintenance(@PathVariable("maintenance-id") Long maintenanceId) {
        return maintenanceService.retrieveMaintenance(maintenanceId);
    }

    // http://localhost:8081/maintenance/add-maintenance
    @Operation(description = "Ajouter une maintenance")
    @PostMapping("/add-maintenance")
    public Maintenance addMaintenance(@RequestBody Maintenance m) {
        return maintenanceService.addMaintenance(m);
    }

    // http://localhost:8081/maintenance/modify-maintenance
    @Operation(description = "Modifier une maintenance")
    @PutMapping("/modify-maintenance")
    public Maintenance modifyMaintenance(@RequestBody Maintenance m) {
        return maintenanceService.updateMaintenance(m);
    }

    // http://localhost:8081/maintenance/remove-maintenance/1
    @Operation(description = "Supprimer une maintenance")
    @DeleteMapping("/remove-maintenance/{maintenance-id}")
    public void removeMaintenance(@PathVariable("maintenance-id") Long maintenanceId) {
        maintenanceService.removeMaintenance(maintenanceId);
    }

    // http://localhost:8081/maintenance/planifier-maintenance/1
    @Operation(description = "Planifier une maintenance pour un véhicule")
    @PostMapping("/planifier-maintenance/{vehicule-id}")
    public Maintenance planifierMaintenance(@PathVariable("vehicule-id") Long vehiculeId,
                                             @RequestBody Maintenance m) {
        return maintenanceService.planifierMaintenance(vehiculeId, m);
    }

    // http://localhost:8081/maintenance/terminer-maintenance/1
    @Operation(description = "Terminer une maintenance")
    @PutMapping("/terminer-maintenance/{maintenance-id}")
    public Maintenance terminerMaintenance(@PathVariable("maintenance-id") Long maintenanceId) {
        return maintenanceService.terminerMaintenance(maintenanceId);
    }
}

