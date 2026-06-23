package fr.eni.ludotheque.Service;

import fr.eni.ludotheque.BO.Adresse;
import fr.eni.ludotheque.BO.Client;
import fr.eni.ludotheque.DAL.ClientRepository;
import fr.eni.ludotheque.dto.ClientDTO;
import fr.eni.ludotheque.exceptions.BusinessException;
import fr.eni.ludotheque.exceptions.EmailClientAlreadyExistException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

