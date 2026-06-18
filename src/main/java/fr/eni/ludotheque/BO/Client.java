package fr.eni.ludotheque.BO;

import jakarta.persistence.*;
import lombok.*;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private int no_client;

    @Column(nullable = false, length = 50)
    private String nom;

    @Column(nullable = false, length = 50)
    private String prenom;

    @Column(unique = true, nullable = false, length = 50)
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "no_adresse")
    private Adresse adresse;

    @Column( nullable = false, length = 10)
    private String no_telephone;

}
