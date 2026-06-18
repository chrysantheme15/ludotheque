package fr.eni.ludotheque.DAL;

import fr.eni.ludotheque.BO.Exemplaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExemplaireRepository extends JpaRepository<Exemplaire, Integer> {

}
