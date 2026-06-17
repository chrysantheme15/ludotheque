package fr.eni.ludotheque.BO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name="CLIENT_OTM")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no_client;

    @Column(unique = true, nullable = false, length = 50)
    private String nom;

    @Column(unique = true, nullable = false, length = 50)
    private String prenom;

    @Column(unique = true, nullable = false, length = 50)
    private String email;

    @OneToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "client_no_client")
    private Adresse adresse;

    @Column(unique = true, nullable = false, length = 10)
    private String no_telephone;

}
