package fr.eni.ludotheque.Service;

import fr.eni.ludotheque.BO.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ClientService {

    //ajouter un client et son adresse
    void ajouterClient(Client client);

    //trouver les clients dont le nom commence par la chaine fournie
    // Méthodes de requêtes avec mots clefs via Spring Data JPA
    List<Client> findByNomStartingWith(String nom);

    // Création d'une requête paramétrée avec JPQL
    //@Query("SELECT c FROM Client c WHERE c.nom LIKE CONCAT(:nom, '%')")
    //List<Client> rechercherParDebutNom(@Param("nom") String nom);

    //Modification complète d'un client

}
