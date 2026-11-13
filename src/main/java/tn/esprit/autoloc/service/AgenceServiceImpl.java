package tn.esprit.autoloc.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.repository.AgenceRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class AgenceServiceImpl implements IAgenceService {

    private final AgenceRepository agenceRepository;

    @Override
    public List<Agence> retrieveAllAgences() {
        return agenceRepository.findAll();
    }

    @Override
    public Agence retrieveAgence(Long idAgence) {
        return agenceRepository.findById(idAgence)
                .orElseThrow(() -> new RuntimeException("Agence introuvable avec l'identifiant : " + idAgence));
    }

    @Override
    public Agence addAgence(Agence agence) {
        return agenceRepository.save(agence);
    }

    @Override
    public Agence updateAgence(Agence agence) {
        if (!agenceRepository.existsById(agence.getIdAgence())) {
            throw new RuntimeException("Impossible de mettre à jour : Agence introuvable avec l'identifiant : " + agence.getIdAgence());
        }
        return agenceRepository.save(agence);
    }

    @Override
    public void removeAgence(Long idAgence) {
        if (!agenceRepository.existsById(idAgence)) {
            throw new RuntimeException("Impossible de supprimer : Agence introuvable avec l'identifiant : " + idAgence);
        }
        agenceRepository.deleteById(idAgence);
    }
}
