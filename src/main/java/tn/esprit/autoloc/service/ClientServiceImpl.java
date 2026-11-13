package tn.esprit.autoloc.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.repository.ClientRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ClientServiceImpl implements IClientService {

    private final ClientRepository clientRepository;

    @Override
    public List<Client> retrieveAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client retrieveClient(Long idClient) {
        return clientRepository.findById(idClient)
                .orElseThrow(() -> new RuntimeException("Client introuvable avec l'identifiant : " + idClient));
    }

    @Override
    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client updateClient(Client client) {
        if (!clientRepository.existsById(client.getIdClient())) {
            throw new RuntimeException("Impossible de mettre à jour : Client introuvable avec l'identifiant : " + client.getIdClient());
        }
        return clientRepository.save(client);
    }

    @Override
    public void removeClient(Long idClient) {
        if (!clientRepository.existsById(idClient)) {
            throw new RuntimeException("Impossible de supprimer : Client introuvable avec l'identifiant : " + idClient);
        }
        clientRepository.deleteById(idClient);
    }
}
