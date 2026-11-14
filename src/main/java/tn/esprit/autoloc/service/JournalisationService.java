package tn.esprit.autoloc.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Service dédié à la journalisation centralisée des événements de l'application (Principe SRP).
 */
@Service
@Slf4j
public class JournalisationService {

    public void logAction(String message) {
        log.info("[AUTOLOC LOG] - {}", message);
    }

    public void logError(String message, Throwable throwable) {
        log.error("[AUTOLOC ERROR] - {}", message, throwable);
    }
}

