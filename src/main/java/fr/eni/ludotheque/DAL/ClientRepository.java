package fr.eni.ludotheque.DAL;

import fr.eni.ludotheque.BO.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {


    //trouver les clients dont le nom commence par la chaine fournie
    List<Client> findByNomStartingWith(String nom);


}
