package fr.eni.ludotheque.BO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Jeux {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no_jeu;

    @Column(unique = true, nullable = false, length = 50)
    private String titre;

    @Column(unique = true, nullable = false, length = 13)
    private String reference;

    @Column(unique = true, nullable = false)
    private int age_min;

    @Column(unique = true, nullable = false, length = 255)
    private String description;

    @Column(unique = true, nullable = false)
    private int duree;

    @Column(unique = true, nullable = false)
    private int tarif_jour;
}
