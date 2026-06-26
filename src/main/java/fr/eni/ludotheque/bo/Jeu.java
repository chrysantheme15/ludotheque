package fr.eni.ludotheque.bo;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="JEUX")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Jeu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="no_jeu")
    private Integer noJeu;

    @Column(length=50, nullable=false)
    @NonNull
    private String titre;

    @EqualsAndHashCode.Include
    @Column(length=13, nullable=false, unique=true)
    @NonNull
    private String reference;

    private int ageMin;

    private String description;

    private int duree;

    @Column(nullable=false)
    @NonNull
    private Float tarifJour;


    @Transient
    private int nbExemplairesDisponibles;


    @OneToMany(mappedBy = "jeu",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Exemplaire> exemplaires = new ArrayList<>();


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "JEUX_GENRES",
            joinColumns = @JoinColumn(name="no_jeu"),
            inverseJoinColumns = @JoinColumn(name="no_genre")
    )
    private List<Genre> genres = new ArrayList<>();


    public void addGenre(Genre g) {
        genres.add(g);
    }
}