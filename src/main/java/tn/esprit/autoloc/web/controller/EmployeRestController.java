package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.service.IEmployeService;

import java.util.List;

@Tag(name = "Gestion des Employés")
@RestController
@AllArgsConstructor
@RequestMapping("/employe")
public class EmployeRestController {

    IEmployeService employeService;

    // http://localhost:8081/employe/retrieve-all-employes
    @Operation(description = "Récupérer tous les employés")
    @GetMapping("/retrieve-all-employes")
    public List<Employe> getEmployes() {
        return employeService.retrieveAllEmployes();
    }

    // http://localhost:8081/employe/retrieve-employe/1
    @Operation(description = "Récupérer un employé par ID")
    @GetMapping("/retrieve-employe/{employe-id}")
    public Employe retrieveEmploye(@PathVariable("employe-id") Long employeId) {
        return employeService.retrieveEmploye(employeId);
    }

    // http://localhost:8081/employe/add-employe
    @Operation(description = "Ajouter un employé")
    @PostMapping("/add-employe")
    public Employe addEmploye(@RequestBody Employe e) {
        return employeService.addEmploye(e);
    }

    // http://localhost:8081/employe/modify-employe
    @Operation(description = "Modifier un employé")
    @PutMapping("/modify-employe")
    public Employe modifyEmploye(@RequestBody Employe e) {
        return employeService.updateEmploye(e);
    }

    // http://localhost:8081/employe/remove-employe/1
    @Operation(description = "Supprimer un employé")
    @DeleteMapping("/remove-employe/{employe-id}")
    public void removeEmploye(@PathVariable("employe-id") Long employeId) {
        employeService.removeEmploye(employeId);
    }

    // http://localhost:8081/employe/affecter-employe/1/2
    @Operation(description = "Affecter un employé à une agence (Scénario RH : mutation avec désaffectation préalable)")
    @PutMapping({"/affecter-employe/{employe-id}/{agence-id}", "/affecter-employe-agence/{employe-id}/{agence-id}"})
    public Employe affecterEmploye(@PathVariable("employe-id") Long employeId,
                                   @PathVariable("agence-id") Long agenceId) {
        return employeService.affecterEmploye(employeId, agenceId);
    }

    // http://localhost:8081/employe/desaffecter-employe/1
    @Operation(description = "Désaffecter un employé de son agence")
    @PutMapping("/desaffecter-employe/{employe-id}")
    public Employe desaffecterEmploye(@PathVariable("employe-id") Long employeId) {
        return employeService.desaffecterEmploye(employeId);
    }
}

