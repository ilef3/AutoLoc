package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Contrat;
import java.util.List;

public interface IContratService {
    List<Contrat> retrieveAllContrats();
    Contrat retrieveContrat(Long idContrat);
    Contrat addContrat(Contrat contrat);
    Contrat updateContrat(Contrat contrat);
    void removeContrat(Long idContrat);
    Contrat creerContratPourReservation(Long idReservation);
    Contrat validerContrat(Long idContrat);
}
