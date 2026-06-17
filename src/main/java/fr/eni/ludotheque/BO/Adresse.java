package fr.eni.ludotheque.BO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Adresse {

    @Id
    @GeneratedValue
    private int no_adresse;

    @Column(unique = true, nullable = false, length = 255)
    private String rue;

    @Column(unique = true, nullable = false, length = 5)
    private String code_postal;

    @Column(unique = true, nullable = false, length = 100)
    private String ville;
}
