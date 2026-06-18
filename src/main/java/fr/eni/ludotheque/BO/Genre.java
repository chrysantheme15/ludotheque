package fr.eni.ludotheque.BO;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "Genre")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    @Column(name = "no_genre")
    private int no_genre;

    @Column( nullable = false, length = 255)
    private String libelle;

    @ManyToMany(mappedBy = "genres")
    private List<Jeu> jeux= new ArrayList<>();

}
