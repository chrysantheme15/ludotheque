package fr.eni.ludotheque.rest;

import fr.eni.ludotheque.BO.Client;
import fr.eni.ludotheque.Service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clients")
public class ClientRestController {

    private final ClientService clientService;

    public ClientRestController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void ajouterClient(@RequestBody Client client) {
        clientService.ajouterClient(client);
    }
}