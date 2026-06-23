package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.bo.Exemplaire;
import fr.eni.ludotheque.bo.Genre;
import fr.eni.ludotheque.bo.Jeu;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ExemplaireRepositoryTest {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private JeuRepository jeuRepository;

    @Autowired
    private ExemplaireRepository exemplaireRepository;

    @Test
    @DisplayName("test création exemplaire cas positif")
    @Transactional
    public void testCreationExemplaire() {

        //Arrange
        Genre genre = new Genre();
        genre.setLibelle("");

        genreRepository.save(genre);

        Jeu jeu = new Jeu();
        jeu.setTitre("Monopoly");
        jeu.setReference("123fgh");
        jeu.setAge_min(6);
        jeu.setDuree(90);
        jeu.setDescription("jeu ludique et créatif");
        jeu.setTarif_jour(5);

        Jeu jeuBD = jeuRepository.save(jeu);
        Exemplaire skyJoBoite1 = new Exemplaire("1414141414141", jeuBD);

        //Act
        Exemplaire exemplaireBD = exemplaireRepository.save(skyJoBoite1);

        //Assert
        Exemplaire exemplaireFromBD = exemplaireRepository.findById(exemplaireBD.getNoExemplaire()).orElse(null);
        assertThat(exemplaireFromBD).isNotNull();
        assertThat(exemplaireFromBD.getNoExemplaire()).isNotNull();
        assertThat(exemplaireFromBD.getCode_barre()).isEqualTo(skyJoBoite1.getCode_barre());
        assertThat(exemplaireFromBD.isLouable()).isEqualTo(skyJoBoite1.isLouable());
        assertThat(exemplaireFromBD.getJeu()).isEqualTo(skyJoBoite1.getJeu());

    }

}
