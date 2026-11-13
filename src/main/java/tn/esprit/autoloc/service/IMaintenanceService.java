package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Maintenance;
import java.util.List;

public interface IMaintenanceService {
    List<Maintenance> retrieveAllMaintenances();
    Maintenance retrieveMaintenance(Long idMaintenance);
    Maintenance addMaintenance(Maintenance maintenance);
    Maintenance updateMaintenance(Maintenance maintenance);
    void removeMaintenance(Long idMaintenance);
    Maintenance planifierMaintenance(Long idVehicule, Maintenance maintenance);
    Maintenance terminerMaintenance(Long idMaintenance);
}
