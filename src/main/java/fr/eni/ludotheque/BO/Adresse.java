package fr.eni.ludotheque.BO;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@RequiredArgsConstructor
@Entity
@AllArgsConstructor
public class Adresse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private int no_adresse;

    @Column( nullable = false, length = 255)
    private String rue;

    @Column( nullable = false, length = 5)
    private String code_postal;

    @Column( nullable = false, length = 100)
    private String ville;
}
