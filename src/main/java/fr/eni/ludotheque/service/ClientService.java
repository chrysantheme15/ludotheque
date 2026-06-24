package fr.eni.ludotheque.service;

import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.dto.AdresseDTO;
import fr.eni.ludotheque.dto.ClientDTO;

import java.util.List;

public interface ClientService {

    //ajouter un client et son adresse
    public Client ajouterClient(ClientDTO clientDto);

    //trouver les clients dont le nom commence par la chaine fournie
    // Méthodes de requêtes avec mots clefs via Spring Data JPA
     public List<Client> trouverClientsParNom(String nom);

    // Création d'une requête paramétrée avec JPQL
    //@Query("SELECT c FROM Client c WHERE c.nom LIKE CONCAT(:nom, '%')")
    //List<Client> rechercherParDebutNom(@Param("nom") String nom);

    //Modification complète d'un client
    public Client modifierClient(Integer noClient, ClientDTO clientDto);

    public Client trouverClientParId(Integer noClient);

    public Client modifierAdresse(Integer no_Client, AdresseDTO adresseDto) ;

    void supprimer(Integer noClient);

}
