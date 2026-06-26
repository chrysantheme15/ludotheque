/**package fr.eni.ludotheque.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PasswordEncoderController {

    @Autowired
    private org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

    @GetMapping("/encode")
    public String chiffreChaine(@RequestParam(name="chaine") String chaine) {
        return passwordEncoder.encode(chaine);
    }

}
*/