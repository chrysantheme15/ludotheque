package fr.eni.ludotheque.rest;

import fr.eni.ludotheque.exceptions.DataNotFound;
import fr.eni.ludotheque.service.ExemplaireService;
import fr.eni.ludotheque.service.JeuService;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/exemplaires")
public class ExemplaireRestController {

    @NonNull
    private final ExemplaireService exemplaireService;

    public ExemplaireRestController(ExemplaireService exemplaireService) {
        this.exemplaireService = exemplaireService;
    }

    @GetMapping("/{codeBarre}")
    public ResponseEntity<?> trouverParCodeBarre(
            @PathVariable String codeBarre) {

        try {

            return ResponseEntity.ok(
                    exemplaireService.trouverParCodeBarre(codeBarre));

        } catch (DataNotFound e) {

            return ResponseEntity.notFound().build();
        }
    }
}
