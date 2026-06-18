package fr.eni.ludotheque.Service;

import fr.eni.ludotheque.BO.Client;
import fr.eni.ludotheque.DAL.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public  ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public void ajouterClient(Client client) {
        clientRepository.save(client);
    }
}

