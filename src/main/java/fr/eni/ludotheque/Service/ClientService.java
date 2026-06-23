package fr.eni.ludotheque.Service;

import fr.eni.ludotheque.BO.Client;
import fr.eni.ludotheque.dto.ClientDTO;
import fr.eni.ludotheque.exceptions.BusinessException;

import java.util.List;

public interface ClientService {

    //ajouter un client et son adresse
    Client ajouterClient(ClientDTO clientDto);

    //trouver les clients dont le nom commence par la chaine fournie
    // Méthodes de requêtes avec mots clefs via Spring Data JPA
    List<Client> findByNomStartingWith(String nom);

    // Création d'une requête paramétrée avec JPQL
    //@Query("SELECT c FROM Client c WHERE c.nom LIKE CONCAT(:nom, '%')")
    //List<Client> rechercherParDebutNom(@Param("nom") String nom);

    //Modification complète d'un client
    void modifierClient(Client client) throws BusinessException;

}
