package tn.esprit.autoloc.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.MaintenanceRepository;
import tn.esprit.autoloc.repository.VehiculeRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class MaintenanceServiceImpl implements IMaintenanceService {

    private final MaintenanceRepository maintenanceRepository;
    private final VehiculeRepository vehiculeRepository;

    @Override
    public List<Maintenance> retrieveAllMaintenances() {
        return maintenanceRepository.findAll();
    }

    @Override
    public Maintenance retrieveMaintenance(Long idMaintenance) {
        return maintenanceRepository.findById(idMaintenance)
                .orElseThrow(() -> new RuntimeException("Maintenance introuvable avec l'identifiant : " + idMaintenance));
    }

    @Override
    public Maintenance addMaintenance(Maintenance maintenance) {
        return maintenanceRepository.save(maintenance);
    }

    @Override
    public Maintenance updateMaintenance(Maintenance maintenance) {
        if (!maintenanceRepository.existsById(maintenance.getIdMaintenance())) {
            throw new RuntimeException("Impossible de mettre à jour : Maintenance introuvable avec l'identifiant : " + maintenance.getIdMaintenance());
        }
        return maintenanceRepository.save(maintenance);
    }

    @Override
    public void removeMaintenance(Long idMaintenance) {
        if (!maintenanceRepository.existsById(idMaintenance)) {
            throw new RuntimeException("Impossible de supprimer : Maintenance introuvable avec l'identifiant : " + idMaintenance);
        }
        maintenanceRepository.deleteById(idMaintenance);
    }

    @Override
    @Transactional
    public Maintenance planifierMaintenance(Long idVehicule, Maintenance maintenance) {
        Vehicule vehicule = vehiculeRepository.findById(idVehicule)
                .orElseThrow(() -> new RuntimeException("Véhicule introuvable avec l'identifiant : " + idVehicule));

        maintenance.setVehicule(vehicule);
        vehicule.setStatut(StatutVehicule.MAINTENANCE);
        vehiculeRepository.save(vehicule);

        return maintenanceRepository.save(maintenance);
    }

    @Override
    @Transactional
    public Maintenance terminerMaintenance(Long idMaintenance) {
        Maintenance maintenance = retrieveMaintenance(idMaintenance);
        if (maintenance.getDateFin() == null) {
            maintenance.setDateFin(LocalDate.now());
        }

        if (maintenance.getVehicule() != null) {
            maintenance.getVehicule().setStatut(StatutVehicule.DISPONIBLE);
            vehiculeRepository.save(maintenance.getVehicule());
        }

        return maintenanceRepository.save(maintenance);
    }
}
