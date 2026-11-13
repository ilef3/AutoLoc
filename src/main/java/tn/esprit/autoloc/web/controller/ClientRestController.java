package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.service.IClientService;

import java.util.List;

@Tag(name = "Gestion des Clients")
@RestController
@AllArgsConstructor
@RequestMapping("/client")
public class ClientRestController {

    IClientService clientService;

    // http://localhost:8081/client/retrieve-all-clients
    @Operation(description = "Récupérer tous les clients")
    @GetMapping("/retrieve-all-clients")
    public List<Client> getClients() {
        List<Client> listClients = clientService.retrieveAllClients();
        return listClients;
    }

    // http://localhost:8081/client/retrieve-client/8
    @Operation(description = "Récupérer un client par ID")
    @GetMapping("/retrieve-client/{client-id}")
    public Client retrieveClient(@PathVariable("client-id") Long clientId) {
        Client client = clientService.retrieveClient(clientId);
        return client;
    }

    // http://localhost:8081/client/add-client
    @Operation(description = "Ajouter un client")
    @PostMapping("/add-client")
    public Client addClient(@RequestBody Client c) {
        Client client = clientService.addClient(c);
        return client;
    }

    // http://localhost:8081/client/remove-client/1
    @Operation(description = "Supprimer un client")
    @DeleteMapping("/remove-client/{client-id}")
    public void removeClient(@PathVariable("client-id") Long clientId) {
        clientService.removeClient(clientId);
    }

    // http://localhost:8081/client/modify-client
    @Operation(description = "Modifier un client")
    @PutMapping({"/modify-client", "/update-client"})
    public Client modifyClient(@RequestBody Client c) {
        Client client = clientService.updateClient(c);
        return client;
    }
}
