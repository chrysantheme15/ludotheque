package fr.eni.ludotheque.Service;

import fr.eni.ludotheque.BO.Adresse;
import fr.eni.ludotheque.BO.Client;
import fr.eni.ludotheque.DAL.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClientServiceTest {

    @Autowired
    ClientService clientService;

    @Autowired
    ClientRepository clientRepository;

    @Test
    void testCreationClient() {
        //Arrange (Préparation du test)
        Adresse adresse = new Adresse();
        adresse.setRue("10 rue de Brest");
        adresse.setCode_postal("29200");
        adresse.setVille("Brest");

        Client client = new Client();
        client.setNom("RAZAFI");
        client.setPrenom("chrys");
        client.setEmail("chrysrazafi@gmail.com");
        client.setAdresse(adresse);
        client.setNo_telephone("0123456789");


        //Act (Appel de la méthode à vérifier)
        clientService.ajouterClient(client);

        //Assert (vérifications)
        Client clientBD = clientRepository.findById(client.getNo_client()).orElse(null);
        Assertions.assertNotNull(clientBD);
        Assertions.assertEquals(client,  clientBD);


    }
}
