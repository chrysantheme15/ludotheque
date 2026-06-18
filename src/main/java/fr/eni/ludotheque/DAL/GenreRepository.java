package fr.eni.ludotheque.DAL;

import fr.eni.ludotheque.BO.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
}
