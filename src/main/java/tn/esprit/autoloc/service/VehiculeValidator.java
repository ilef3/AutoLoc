package tn.esprit.autoloc.service;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Vehicule;

import java.math.BigDecimal;

/**
 * Composant dédié à la validation des règles métier sur les véhicules (Principe SRP).
 */
@Component
public class VehiculeValidator {

    public void valider(Vehicule vehicule) {
        if (vehicule == null) {
            throw new IllegalArgumentException("Le véhicule ne peut pas être null");
        }
        if (vehicule.getTarifJournalier() == null || vehicule.getTarifJournalier().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Le tarif journalier doit être strictement positif");
        }
        if (vehicule.getImmatriculation() == null || vehicule.getImmatriculation().trim().isEmpty()) {
            throw new IllegalArgumentException("L'immatriculation est obligatoire");
        }
    }
}

