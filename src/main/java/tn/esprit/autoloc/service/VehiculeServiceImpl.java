package tn.esprit.autoloc.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.AgenceRepository;
import tn.esprit.autoloc.repository.EquipementRepository;
import tn.esprit.autoloc.repository.VehiculeRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class VehiculeServiceImpl implements IVehiculeService {

    private final VehiculeRepository vehiculeRepository;
    private final AgenceRepository agenceRepository;
    private final EquipementRepository equipementRepository;

    @Override
    public List<Vehicule> retrieveAllVehicules() {
        return vehiculeRepository.findAll();
    }

    @Override
    public Vehicule retrieveVehicule(Long idVehicule) {
        return vehiculeRepository.findById(idVehicule)
                .orElseThrow(() -> new RuntimeException("Véhicule introuvable avec l'identifiant : " + idVehicule));
    }

    @Override
    public Vehicule addVehicule(Vehicule vehicule) {
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public Vehicule updateVehicule(Vehicule vehicule) {
        if (!vehiculeRepository.existsById(vehicule.getIdVehicule())) {
            throw new RuntimeException("Impossible de mettre à jour : Véhicule introuvable avec l'identifiant : " + vehicule.getIdVehicule());
        }
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public void removeVehicule(Long idVehicule) {
        if (!vehiculeRepository.existsById(idVehicule)) {
            throw new RuntimeException("Impossible de supprimer : Véhicule introuvable avec l'identifiant : " + idVehicule);
        }
        vehiculeRepository.deleteById(idVehicule);
    }

    @Override
    @Transactional
    public Vehicule affecterVehiculeAAgence(Long idVehicule, Long idAgence) {
        Vehicule vehicule = retrieveVehicule(idVehicule);
        Agence agence = agenceRepository.findById(idAgence)
                .orElseThrow(() -> new RuntimeException("Agence introuvable avec l'identifiant : " + idAgence));
        vehicule.setAgence(agence);
        return vehiculeRepository.save(vehicule);
    }

    @Override
    @Transactional
    public Vehicule affecterEquipementsAVehicule(Long idVehicule, List<Long> idEquipements) {
        Vehicule vehicule = retrieveVehicule(idVehicule);
        List<Equipement> equipements = equipementRepository.findAllById(idEquipements);
        vehicule.setEquipements(equipements);
        return vehiculeRepository.save(vehicule);
    }
}
