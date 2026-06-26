package fr.eni.ludotheque.bo;

import jakarta.persistence.*;
import lombok.*;

@Data
@RequiredArgsConstructor
@Entity
@AllArgsConstructor
public class Adresse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private int noAdresse;

    @Column( nullable = false, length = 255)
    private String rue;

    @Column( nullable = false, length = 5)
    private String codePostal;

    @Column( nullable = false, length = 100)
    private String ville;

}
