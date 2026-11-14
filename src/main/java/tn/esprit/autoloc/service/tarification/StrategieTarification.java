package tn.esprit.autoloc.service.tarification;

/**
 * Interface définissant la stratégie de calcul du tarif (Patron de conception GoF Strategy).
 */
public interface StrategieTarification {
    double calculer(double tarifJournalier, int jours);
}

