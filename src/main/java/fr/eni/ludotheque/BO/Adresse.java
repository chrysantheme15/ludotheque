package fr.eni.ludotheque.BO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@Table(name="AdresseOTM")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Adresse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no_adresse;

    @Column(unique = true, nullable = false, length = 255)
    private String rue;

    @Column(unique = true, nullable = false, length = 5)
    private String code_postal;

    @Column(unique = true, nullable = false, length = 100)
    private String ville;
}
