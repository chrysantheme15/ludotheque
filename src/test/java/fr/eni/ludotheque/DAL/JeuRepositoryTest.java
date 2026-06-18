package fr.eni.ludotheque.DAL;

import fr.eni.ludotheque.BO.Genre;
import fr.eni.ludotheque.BO.Jeu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class JeuRepositoryTest {

    @Autowired
    private JeuRepository jeuRepository;
    @Autowired
    private GenreRepository genreRepository;

    @Test
    @DisplayName("test positif de création d'un client en BDD")
    public void testInsertionJeu() {
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


        //Act
        Jeu jeuActual = jeuRepository.save(jeu);

        //Assert
        Jeu jeuBD = jeuRepository.findById(jeuActual.getNo_jeu()).orElse(null);
        assertThat(jeuBD).isNotNull();
        assertThat(jeuBD.getNo_jeu()).isNotNull();
        assertThat(jeuBD.getTitre()).isEqualTo(jeu.getTitre());
        assertThat(jeuBD.getDescription()).isEqualTo(jeu.getDescription());
        assertThat(jeuBD.getAge_min()).isEqualTo(jeu.getAge_min());
        assertThat(jeuBD.getGenres()).hasSize(2);
        assertThat(jeuBD.getReference()).isEqualTo(jeu.getReference());

    }
}
