package fr.eni.ludotheque.service;

import fr.eni.ludotheque.bo.Adresse;
import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.dal.AdresseRepository;
import fr.eni.ludotheque.dal.ClientRepository;
import fr.eni.ludotheque.dto.AdresseDTO;
import fr.eni.ludotheque.dto.ClientDTO;
import fr.eni.ludotheque.exceptions.DataNotFound;
import fr.eni.ludotheque.exceptions.EmailClientAlreadyExistException;
import lombok.NonNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @NonNull
    private final ClientRepository clientRepository;

    @NonNull
    private AdresseRepository adresseRepository;

    @Autowired
    public ClientServiceImpl(
            ClientRepository clientRepository,
            AdresseRepository adresseRepository) {

        this.clientRepository = clientRepository;
        this.adresseRepository = adresseRepository;
    }
    @Override
    public Client ajouterClient(ClientDTO clientDto) {
        Client client = new Client();
        Adresse adresse = new Adresse();

        BeanUtils.copyProperties(clientDto, client);
        BeanUtils.copyProperties(clientDto, adresse);
        client.setAdresse(adresse);
        Client newClient = null;
        try {
            newClient = clientRepository.save(client);
        }catch(DataIntegrityViolationException ex) {
            throw new EmailClientAlreadyExistException();
        }

        return newClient;
    }

    @Override
    public List<Client> trouverClientsParNom(String nom) {
        List<Client> clients = clientRepository.findByNomStartsWith(nom);

        if (clients.isEmpty()) {
            throw new DataNotFound("Client", nom);
        }

        return clients;
    }

    @Override
    public Client modifierClient(Integer noClient, ClientDTO clientDto) {

        Client client = new Client();
        client.setNoClient(noClient);
        client.setAdresse(new Adresse());

        BeanUtils.copyProperties(clientDto, client);
        BeanUtils.copyProperties(clientDto, client.getAdresse());

        Client clientBD = null;

        try {
            clientBD = clientRepository.save(client);
        } catch (OptimisticLockingFailureException e) {
            throw new DataNotFound("Client", noClient);
        }

        return clientBD;
    }

    @Override
    public Client trouverClientParId(Integer id) {
        Optional<Client> optClient = clientRepository.findById(id);
        if(optClient.isEmpty()) {
            throw new DataNotFound("Client", id);
        }
        return optClient.get();
    }

    @Override
    public Client modifierAdresse(Integer no_Client, AdresseDTO adresseDto) {
        Client client = clientRepository.findById(no_Client).orElseThrow(()->new DataNotFound("Client", no_Client));

        BeanUtils.copyProperties(adresseDto, client.getAdresse());

        adresseRepository.save(client.getAdresse());

        return client;
    }

    @Override
    public void supprimer(Integer noClient ) {
        Client client = clientRepository.findById(noClient)

                .orElseThrow(() -> new RuntimeException("Client introuvable"));

        clientRepository.delete(client);
    }
}

