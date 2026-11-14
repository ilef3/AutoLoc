package tn.esprit.autoloc.service.tarification;

import org.springframework.stereotype.Component;

/**
 * Stratégie de tarification standard : tarif journalier * nombre de jours.
 */
@Component("STANDARD")
public class TarificationStandard implements StrategieTarification {

    @Override
    public double calculer(double tarifJournalier, int jours) {
        return tarifJournalier * jours;
    }
}

