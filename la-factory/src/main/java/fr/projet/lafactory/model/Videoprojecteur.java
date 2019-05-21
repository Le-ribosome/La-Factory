package fr.projet.lafactory.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Videoprojecteur")
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "VID_ID")),
		@AttributeOverride(name = "version", column = @Column(name = "VID_VERSION")),
		@AttributeOverride(name = "code", column = @Column(name = "VID_CODE")),
		@AttributeOverride(name = "coutJournee", column = @Column(name = "VID_COUTJOURNEE")) })

public class Videoprojecteur extends Materiel {

	@OneToOne(mappedBy = "videoprojecteur")
	private Salle salle;

	public Salle getSalle() {
		return salle;
	}

	public void setSalle(Salle salle) {
		this.salle = salle;
	}

}
