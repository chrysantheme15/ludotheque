package fr.eni.ludotheque.Service;

import fr.eni.ludotheque.BO.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ClientService {

    void ajouterClient(Client client);

    // Création d'une requête paramétrée avec JPQL
    @Query("SELECT c FROM Client c WHERE c.nom LIKE CONCAT(:nom, '%')")
    List<Client> rechercherParDebutNom(@Param("nom") String nom);

    /**
     * // Méthodes de requêtes avec mots clefs via Spring Data JPA
     *         List<Client> clients = clientRepository.findByNomStartingWith("Ch");
     *
     *
     *         // Méthodes de requêtes avec mots clefs via Spring Data JPA
     *         //List<Client> findByNomStartingWith(String nom);
     */
}
