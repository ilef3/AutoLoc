package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.service.tarification.TarificationService;

import java.util.Set;

@Tag(name = "Gestion de la Tarification (Pattern Strategy & OCP)", description = "APIs pour le calcul dynamique des tarifs de location selon différentes politiques")
@RestController
@RequestMapping("/tarification")
@AllArgsConstructor
public class TarificationRestController {

    private final TarificationService tarificationService;

    @Operation(summary = "Calculer le tarif d'une location selon une stratégie (STANDARD, WEEKEND, LONGUE_DUREE, ENTREPRISE)")
    @GetMapping("/calculer")
    public double calculerTarif(
            @Parameter(description = "Type de politique tarifaire : STANDARD, WEEKEND, LONGUE_DUREE, ENTREPRISE", example = "STANDARD")
            @RequestParam String typeTarif,
            @Parameter(description = "Tarif journalier du véhicule en DT", example = "100.0")
            @RequestParam double tarifJournalier,
            @Parameter(description = "Nombre de jours de location", example = "5")
            @RequestParam int jours) {
        return tarificationService.calculerTarif(typeTarif, tarifJournalier, jours);
    }

    @Operation(summary = "Lister les politiques tarifaires disponibles")
    @GetMapping("/politiques")
    public Set<String> getPolitiquesDisponibles() {
        return tarificationService.getStrategies().keySet();
    }
}

