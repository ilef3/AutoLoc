package tn.esprit.autoloc.service.tarification;

import org.springframework.stereotype.Component;

/**
 * Stratégie de tarification entreprise : réduction de 15%.
 * Ajoutée sans modifier TarificationService (Principe OCP - Open/Closed Principle).
 */
@Component("ENTREPRISE")
public class TarificationEntreprise implements StrategieTarification {

    @Override
    public double calculer(double tarifJournalier, int jours) {
        return tarifJournalier * jours * 0.85;
    }
}

