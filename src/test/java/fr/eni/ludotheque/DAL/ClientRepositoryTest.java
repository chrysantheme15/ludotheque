package fr.eni.ludotheque.DAL;

import fr.eni.ludotheque.BO.Adresse;
import fr.eni.ludotheque.BO.Client;
import fr.eni.ludotheque.Service.ClientService;
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

}