package fr.eni.ludotheque.Service;

import fr.eni.ludotheque.BO.Adresse;
import fr.eni.ludotheque.BO.Client;
import fr.eni.ludotheque.DAL.ClientRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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

    @Test
    @DisplayName("Recherche des clients dont le nom commence par une chaîne")
    @Transactional
    public void TestFindByNomStartingWith(){
        // AAA
        // Arrange : preparation du test
        Adresse adresse1 = new Adresse();
        adresse1.setRue("rue de Palestine");
        adresse1.setCode_postal("29000");
        adresse1.setVille("Quimper");

        Adresse adresse2 = new Adresse();
        adresse2.setRue("rue de Strasbourg");
        adresse2.setCode_postal("29217");
        adresse2.setVille("Plougonvelin");

        Client client1 = new Client();
        client1.setNom("Durand");
        client1.setPrenom("Clodette");
        client1.setEmail( "clodettedurand@mail.com");
        client1.setAdresse( adresse1);
        client1.setNo_telephone("0123456789");

        Client client2 = new Client();
        client2.setNom("Dédé");
        client2.setPrenom("François");
        client2.setEmail("francoisdede@mail.com");
        client2.setAdresse(adresse2);
        client2.setNo_telephone("0891234567");

        //Act
        clientRepository.save(client1);
        clientRepository.save(client2);

        //Assert
        List<Client> resultats  = clientRepository.findByNomStartingWith("D");
        assertThat(resultats).hasSize(2);
        assertThat(resultats)
                .extracting(Client::getNom)
                .contains("Durand", "Dédé");
    }

    @Test
    @DisplayName("Les informations du client et de son adresse sont modifiées")
    public void testModifierClient() throws BusinessException {
        // Arrange
        Adresse adresse = new Adresse();
        adresse.setRue("Rue des lacs");
        adresse.setCode_postal("29200");
        adresse.setVille("Brest");

        Client client = new Client();
        client.setNom("Durand");
        client.setPrenom("Paul");
        client.setEmail("paul@mail.com");
        client.setNo_telephone("0123456789");
        client.setAdresse(adresse);

        Client clientBD = clientRepository.save(client);

        // Act
        clientBD.setNom("Dupont");
        clientBD.getAdresse().setVille("Quimper");

        clientService.modifierClient(clientBD);

        // Assert
        Client clientModifie =
                clientRepository.findById(clientBD.getNo_client()).orElse(null);
        assertThat(clientModifie).isNotNull();
        assertThat(clientModifie.getNom()).isEqualTo("Dupont");
        assertThat(clientModifie.getAdresse().getVille()).isEqualTo("Quimper");
    }

}
