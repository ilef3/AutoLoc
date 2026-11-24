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
    private final VehiculeValidator vehiculeValidator;
    private final JournalisationService journalisationService;

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
        // Application du principe SRP : délégation de la validation et du logging
        vehiculeValidator.valider(vehicule);
        journalisationService.logAction("Ajout d'un nouveau véhicule : " + vehicule.getImmatriculation() + " (" + vehicule.getMarque() + " " + vehicule.getModele() + ")");
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
        return affecterVehicule(idVehicule, idAgence);
    }

    @Override
    @Transactional
    public Vehicule affecterVehicule(Long idVehicule, Long idAgence) {
        Vehicule vehicule = retrieveVehicule(idVehicule);
        Agence agence = agenceRepository.findById(idAgence)
                .orElseThrow(() -> new RuntimeException("Agence introuvable avec l'identifiant : " + idAgence));
        // Règle JPA : Côté propriétaire de la relation (@ManyToOne dans Vehicule)
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

    @Override
    @Transactional
    public Vehicule affecterEquipement(Long idVehicule, Long idEquipement) {
        Vehicule vehicule = retrieveVehicule(idVehicule);
        Equipement equipement = equipementRepository.findById(idEquipement)
                .orElseThrow(() -> new RuntimeException("Equipement introuvable avec l'identifiant : " + idEquipement));
        // Règle JPA ManyToMany : côté parent choisi (Vehicule)
        if (vehicule.getEquipements() == null) {
            vehicule.setEquipements(new java.util.ArrayList<>());
        }
        if (!vehicule.getEquipements().contains(equipement)) {
            vehicule.getEquipements().add(equipement);
        }
        return vehiculeRepository.save(vehicule);
    }

    @Override
    @Transactional
    public Vehicule desaffecterEquipement(Long idVehicule, Long idEquipement) {
        Vehicule vehicule = retrieveVehicule(idVehicule);
        Equipement equipement = equipementRepository.findById(idEquipement)
                .orElseThrow(() -> new RuntimeException("Equipement introuvable avec l'identifiant : " + idEquipement));
        // Règle JPA Désaffectation ManyToMany : enlever l'élément de la liste du parent
        if (vehicule.getEquipements() != null) {
            vehicule.getEquipements().remove(equipement);
        }
        return vehiculeRepository.save(vehicule);
    }
}
