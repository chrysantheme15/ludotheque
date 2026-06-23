package fr.eni.ludotheque.rest;

import fr.eni.ludotheque.BO.Client;
import fr.eni.ludotheque.Service.ClientService;
import fr.eni.ludotheque.dto.ClientDTO;
import fr.eni.ludotheque.exceptions.EmailClientAlreadyExistException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clients")
public class ClientRestController {

    @NonNull
    private final ClientService clientService;

    public ClientRestController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Client> ajouterClient(@RequestBody ClientDTO clientDto){

        Client client = null;

        try{
            client = clientService.ajouterClient(clientDto);
        }catch(EmailClientAlreadyExistException exc){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        return  ResponseEntity.status(HttpStatus.CREATED).body(client);

    }
}