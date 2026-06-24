package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.bo.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LocationRepository extends JpaRepository<Location, Integer>{
    @Query("""
        SELECT l
        FROM Location l
        JOIN FETCH l.exemplaire e
        JOIN FETCH e.jeu
        WHERE e.codebarre = :codebarre
    """)
    Location findLocationByCodebarreWithJeu(
            @Param("codebarre") String codebarre
    );

    @Query("""
    SELECT COUNT(l) > 0
    FROM Location l
    WHERE l.exemplaire.noExemplaire = :noExemplaire
    AND l.dateRetour IS NULL
    """)
    boolean exemplaireDejaLouee(
            @Param("noExemplaire") Integer noExemplaire
    );
}
