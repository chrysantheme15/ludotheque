package fr.eni.ludotheque.bo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Collection;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name="GENRES")
public class Genre {
	@Id
	@NonNull
	private Integer noGenre;

	@NonNull private String libelle;

	@ManyToMany(mappedBy = "genres")
	private Collection<Jeu> jeus;

	public Collection<Jeu> getJeus() {
		return jeus;
	}

	public void setJeus(Collection<Jeu> jeus) {
		this.jeus = jeus;
	}
}
