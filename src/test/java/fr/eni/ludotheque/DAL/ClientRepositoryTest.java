package fr.eni.ludotheque.DAL;

import fr.eni.ludotheque.BO.Adresse;
import fr.eni.ludotheque.BO.Client;
import jakarta.transaction.Transactional;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    @DisplayName("test positif de création d'un client en BDD")
    @Transactional
    public void testInsertClientRepository() {
        // AAA
        // Arrange : preparation du test

        Adresse adresse = new Adresse();
        adresse.setRue("10 rue de Brest");
        adresse.setCode_postal("29200");
        adresse.setVille("Brest");

        //adresseRepository.save(adresse); avec cascade = CascadeType.ALL, adresseRepository.save(adresse) est inutile.

        Client client = new Client();
        client.setNom("Chrys");
        client.setPrenom("Sisi");
        client.setEmail("Chrys@gmail.com");
        client.setAdresse(adresse);
        client.setNo_telephone("0123456789");


        // Act
        Client clientBD = clientRepository.save(client) ;

        //Assert
        assertThat(clientBD).isNotNull();
        assertThat(clientBD.getNo_client()).isNotNull();
        assertThat(clientBD.getAdresse()).isNotNull();
        assertThat(clientBD.getAdresse().getVille()).isEqualTo("Brest");

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
}