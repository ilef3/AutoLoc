package tn.esprit.autoloc.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.repository.EquipementRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class EquipementServiceImpl implements IEquipementService {

    private final EquipementRepository equipementRepository;

    @Override
    public List<Equipement> retrieveAllEquipements() {
        return equipementRepository.findAll();
    }

    @Override
    public Equipement retrieveEquipement(Long idEquipement) {
        return equipementRepository.findById(idEquipement)
                .orElseThrow(() -> new RuntimeException("Équipement introuvable avec l'identifiant : " + idEquipement));
    }

    @Override
    public Equipement addEquipement(Equipement equipement) {
        return equipementRepository.save(equipement);
    }

    @Override
    public Equipement updateEquipement(Equipement equipement) {
        if (!equipementRepository.existsById(equipement.getIdEquipement())) {
            throw new RuntimeException("Impossible de mettre à jour : Équipement introuvable avec l'identifiant : " + equipement.getIdEquipement());
        }
        return equipementRepository.save(equipement);
    }

    @Override
    public void removeEquipement(Long idEquipement) {
        if (!equipementRepository.existsById(idEquipement)) {
            throw new RuntimeException("Impossible de supprimer : Équipement introuvable avec l'identifiant : " + idEquipement);
        }
        equipementRepository.deleteById(idEquipement);
    }
}
