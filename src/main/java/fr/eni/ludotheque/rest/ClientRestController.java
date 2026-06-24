package fr.eni.ludotheque.rest;

import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.dto.AdresseDTO;
import fr.eni.ludotheque.exceptions.DataNotFound;
import fr.eni.ludotheque.service.ClientService;
import fr.eni.ludotheque.dto.ClientDTO;
import fr.eni.ludotheque.exceptions.EmailClientAlreadyExistException;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientRestController {

    @NonNull
    private final ClientService clientService;

    public ClientRestController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<Client> ajouterClient(@RequestBody ClientDTO clientDto){

        Client client = null;

        try{
            client = clientService.ajouterClient(clientDto);
        }catch(EmailClientAlreadyExistException exc){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        return  ResponseEntity.status(HttpStatus.CREATED).body(client);

    }
    @DeleteMapping("/{noClient}")
    public ResponseEntity<?> supprimerClient(@PathVariable Integer noClient) {

        System.out.println("CONTROLLER : suppression de " + noClient);

        try {
            clientService.supprimer(noClient);

            System.out.println("CONTROLLER : suppression OK");

            return ResponseEntity.noContent().build();

        } catch (Exception e) {

            System.out.println("ERREUR : " + e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @PutMapping("/{noClient}")
    public ResponseEntity<?> modifierClient(
            @PathVariable Integer noClient,
            @RequestBody ClientDTO clientDto) {

        try {

            Client clientModifie = clientService.modifierClient(noClient, clientDto);

            return ResponseEntity.ok(clientModifie);

        } catch (Exception e) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @PutMapping("/{noClient}/adresse")
    public ResponseEntity<?> modifierAdresse(
            @PathVariable Integer noClient,
            @RequestBody AdresseDTO adresseDTO) {

        try {

            clientService.modifierAdresse(noClient, adresseDTO);

            return ResponseEntity.ok().build();

        } catch (DataNotFound e) {

            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Client>> trouverClientsParNom(
            @RequestParam String nom) {

        try {

            return ResponseEntity.ok(
                    clientService.trouverClientsParNom(nom));

        } catch (DataNotFound e) {

            return ResponseEntity.notFound().build();
        }
    }
}