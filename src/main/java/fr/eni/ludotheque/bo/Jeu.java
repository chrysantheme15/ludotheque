package fr.eni.ludotheque.bo;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "Jeu")
public class Jeu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    @Column(name="no_jeu")
    private int no_jeu;

    @Column( nullable = false, length = 50)
    private String titre;

    @Column( nullable = false, length = 13, unique = true)
    private String reference;

    @Column( nullable = false)
    private int age_min;

    @Column( nullable = false, length = 255)
    private String description;

    @Column( nullable = false)
    private int duree;

    @Column( nullable = false)
    private int tarif_jour;

    @ManyToMany
    @JoinTable(
            name = "Jeu_Genre",
            joinColumns = @JoinColumn(name = "no_jeu"),
            inverseJoinColumns = @JoinColumn(name = "no_genre")
            )
    private List<Genre> genres = new ArrayList<>();

}
