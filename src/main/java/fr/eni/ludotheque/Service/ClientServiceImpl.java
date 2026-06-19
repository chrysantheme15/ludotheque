package fr.eni.ludotheque.Service;

import fr.eni.ludotheque.BO.Client;
import fr.eni.ludotheque.DAL.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<Client> findByNomStartingWith(String nom) {
        return clientRepository.findByNomStartingWith(nom);
    }

    @Override
    public void modifierClient(Client client) throws BusinessException {
        Client clientExistant = clientRepository.findById(client.getNo_client())
                .orElseThrow(() -> new BusinessException("Client introuvable"));

        clientExistant.setNom(client.getNom());
        clientExistant.setPrenom(client.getPrenom());
        clientExistant.setEmail(client.getEmail());
        clientExistant.setNo_telephone(client.getNo_telephone());

        clientExistant.getAdresse().setRue(client.getAdresse().getRue());
        clientExistant.getAdresse().setCode_postal(client.getAdresse().getCode_postal());
        clientExistant.getAdresse().setVille(client.getAdresse().getVille());

        clientRepository.save(clientExistant);
    }
}

