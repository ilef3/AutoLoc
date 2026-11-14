package tn.esprit.autoloc.service.tarification;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Service orchestrant le calcul de tarification via le patron de conception Strategy.
 * Conforme au principe OCP (Open/Closed Principle) : ouvert à l'extension, fermé à la modification.
 */
@Service
public class TarificationService {

    private final Map<String, StrategieTarification> strategies;

    public TarificationService(Map<String, StrategieTarification> strategiesMap) {
        this.strategies = new HashMap<>();
        // Enregistre les stratégies avec des clés normalisées (en majuscules)
        if (strategiesMap != null) {
            strategiesMap.forEach((key, strategy) -> this.strategies.put(key.toUpperCase().replace("-", "_"), strategy));
        }
    }

    public double calculerTarif(String typeTarif, double tarifJournalier, int jours) {
        if (typeTarif == null || typeTarif.trim().isEmpty()) {
            throw new IllegalArgumentException("Le type de tarif ne peut pas être vide");
        }
        if (jours <= 0) {
            throw new IllegalArgumentException("Le nombre de jours doit être strictement positif");
        }
        if (tarifJournalier <= 0) {
            throw new IllegalArgumentException("Le tarif journalier doit être strictement positif");
        }

        String cle = typeTarif.trim().toUpperCase().replace("-", "_");
        StrategieTarification strategie = strategies.get(cle);

        if (strategie == null) {
            throw new IllegalArgumentException("Politique tarifaire inconnue : '" + typeTarif + 
                    "'. Politiques disponibles : " + strategies.keySet());
        }

        return strategie.calculer(tarifJournalier, jours);
    }

    public Map<String, StrategieTarification> getStrategies() {
        return strategies;
    }
}

