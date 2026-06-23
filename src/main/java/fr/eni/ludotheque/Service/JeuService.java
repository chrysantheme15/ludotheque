package fr.eni.ludotheque.Service;

import fr.eni.ludotheque.bo.Jeu;

import java.util.List;

public interface JeuService {
	
	void ajouterJeu(Jeu jeu);
	
	Jeu trouverJeuParNoJeu(Integer noJeu);
	
	List<Jeu> listeJeuxCatalogue(String filtreTitre);
		
}
