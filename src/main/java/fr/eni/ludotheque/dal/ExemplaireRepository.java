package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.bo.Exemplaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ExemplaireRepository extends JpaRepository<Exemplaire, Integer> {

    @Query(nativeQuery = true,
            value = """
            select count(e.code_barre)
            from jeux j 
            inner join exemplaire e
            on j.no_jeu = e.no_jeu
            where j.no_jeu = :noJeu
            and e.louable = true
            and e.no_exemplaire not in (
                select l.no_exemplaire 
                from locations l
                where l.date_retour IS null
            )
            group by e.code_barre
            """)
    int nbExemplairesDisponibleByNoJeu(@Param("noJeu") Integer noJeu);


    Exemplaire findByCodeBarre(String codeBarre);
}