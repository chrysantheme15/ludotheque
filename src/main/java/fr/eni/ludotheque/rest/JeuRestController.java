package fr.eni.ludotheque.rest;

import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.bo.Jeu;
import fr.eni.ludotheque.exceptions.DataNotFound;
import fr.eni.ludotheque.service.ClientService;
import fr.eni.ludotheque.service.JeuService;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/jeux")
public class JeuRestController {

    @NonNull
    private final JeuService jeuService;

    public JeuRestController(JeuService jeuService) {
        this.jeuService = jeuService;
    }

    @GetMapping
    public ResponseEntity<List<Jeu>> listeJeuxCatalogue(
            @RequestParam(required = false) String filtreTitre) {
        try{
            return ResponseEntity.ok(
                    jeuService.listeJeuxCatalogue(filtreTitre));
        }catch (DataNotFound e){
            return ResponseEntity.notFound().build();

        }
    }

}
