package tn.esprit.autoloc.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.repository.AgenceRepository;
import tn.esprit.autoloc.repository.EmployeRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class EmployeServiceImpl implements IEmployeService {

    private final EmployeRepository employeRepository;
    private final AgenceRepository agenceRepository;

    @Override
    public List<Employe> retrieveAllEmployes() {
        return employeRepository.findAll();
    }

    @Override
    public Employe retrieveEmploye(Long idEmploye) {
        return employeRepository.findById(idEmploye)
                .orElseThrow(() -> new RuntimeException("Employé introuvable avec l'identifiant : " + idEmploye));
    }

    @Override
    public Employe addEmploye(Employe employe) {
        return employeRepository.save(employe);
    }

    @Override
    public Employe updateEmploye(Employe employe) {
        if (!employeRepository.existsById(employe.getIdEmploye())) {
            throw new RuntimeException("Impossible de mettre à jour : Employé introuvable avec l'identifiant : " + employe.getIdEmploye());
        }
        return employeRepository.save(employe);
    }

    @Override
    public void removeEmploye(Long idEmploye) {
        if (!employeRepository.existsById(idEmploye)) {
            throw new RuntimeException("Impossible de supprimer : Employé introuvable avec l'identifiant : " + idEmploye);
        }
        employeRepository.deleteById(idEmploye);
    }

    @Override
    @Transactional
    public Employe affecterEmployeAAgence(Long idEmploye, Long idAgence) {
        return affecterEmploye(idEmploye, idAgence);
    }

    @Override
    @Transactional
    public Employe desaffecterEmploye(Long idEmploye) {
        Employe employe = retrieveEmploye(idEmploye);
        employe.setAgence(null);
        return employeRepository.save(employe);
    }

    @Override
    @Transactional
    public Employe affecterEmploye(Long idEmploye, Long idAgence) {
        Employe employe = retrieveEmploye(idEmploye);
        // Scénario 1 : Un employé ne peut appartenir qu’à une seule agence à la fois,
        // donc un transfert passe d’abord par une désaffectation
        if (employe.getAgence() != null) {
            employe.setAgence(null);
        }
        Agence agence = agenceRepository.findById(idAgence)
                .orElseThrow(() -> new RuntimeException("Agence introuvable avec l'identifiant : " + idAgence));
        // Côté propriétaire de la relation (@ManyToOne dans Employe)
        employe.setAgence(agence);
        return employeRepository.save(employe);
    }
}
