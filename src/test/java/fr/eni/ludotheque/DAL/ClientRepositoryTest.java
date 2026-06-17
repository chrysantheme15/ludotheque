package fr.eni.ludotheque.DAL;

import fr.eni.ludotheque.BO.Client;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    @Transactional
    public void testInsertClientRepository() {
        // AAA
        // Arrange :  preparation du test
        Client client = new Client();
        client.setNom("Chrys");
        client.setPrenom("Sisi");
        client.setEmail("Chrys@gmail.com");
        client.setNo_telephone("0123456789");


        // Act
        Client clientBD = clientRepository.save(client) ;

        //Assert
        assertThat(clientBD).isNotNull();
        assertThat(clientBD.getNo_client()).isNotNull();
        assertThat(clientBD.getNom()).isNotNull();
        assertThat(clientBD.getPrenom()).isNotNull();
        assertThat(clientBD.getEmail()).isNotNull();
        assertThat(clientBD.getNo_telephone()).isNotNull();
    }
}
