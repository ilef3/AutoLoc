package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Vehicule;
import java.util.List;

public interface IVehiculeService {
    List<Vehicule> retrieveAllVehicules();
    Vehicule retrieveVehicule(Long idVehicule);
    Vehicule addVehicule(Vehicule vehicule);
    default Vehicule ajouter(Vehicule vehicule) {
        return addVehicule(vehicule);
    }
    Vehicule updateVehicule(Vehicule vehicule);
    void removeVehicule(Long idVehicule);
    Vehicule affecterVehiculeAAgence(Long idVehicule, Long idAgence);
    Vehicule affecterEquipementsAVehicule(Long idVehicule, List<Long> idEquipements);
}
