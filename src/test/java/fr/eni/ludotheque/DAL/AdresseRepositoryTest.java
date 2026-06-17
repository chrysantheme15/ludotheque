package fr.eni.ludotheque.DAL;

import fr.eni.ludotheque.BO.Adresse;
import fr.eni.ludotheque.BO.Adresse;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class AdresseRepositoryTest {

    @Autowired
    private AdresseRepository adresseRepository;

    @Test
    @Transactional
    public void testInsertAdresseRepository() {
        // AAA
        // Arrange :  preparation du test
        Adresse adresse = new Adresse();
        adresse.setRue(" rue de Brest");
        adresse.setCode_postal("29000");
        adresse.setVille("Quimper");


        // Act
        Adresse adresseBD = adresseRepository.save(adresse) ;

        //Assert
        assertThat(adresseBD).isNotNull();
        assertThat(adresseBD.getNo_adresse()).isNotNull();
        assertThat(adresseBD.getRue()).isNotNull();
        assertThat(adresseBD.getCode_postal()).isNotNull();
        assertThat(adresseBD.getVille()).isNotNull();

    }
}
