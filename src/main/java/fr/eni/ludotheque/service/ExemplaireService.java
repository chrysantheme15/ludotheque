package fr.eni.ludotheque.service;

import fr.eni.ludotheque.bo.Exemplaire;

public interface ExemplaireService {
    Exemplaire trouverParCodeBarre(String codeBarre);
}
