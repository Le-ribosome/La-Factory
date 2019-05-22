package fr.projet.lafactory.model;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonView;

import fr.projet.lafactory.model.view.JsonViews;

@Entity
@Table(name = "Salle")
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "SAL_ID")),
		@AttributeOverride(name = "version", column = @Column(name = "SAL_VERSION")),
		@AttributeOverride(name = "code", column = @Column(name = "SAL_CODE")),
		@AttributeOverride(name = "coutJournee", column = @Column(name = "SAL_COUTJOURNEE")) })
public class Salle extends Materiel {

	@JsonView(JsonViews.Salle.class)
	@Column(name = "SAL_CAPACITE")
	@NotNull
	private int capacite;

	@JsonView(JsonViews.SalleAvecVideoprojecteur.class)
	@OneToOne()
	@JoinColumn(name = "SAL_VIDEOPROJECTEUR_ID", nullable = true)
	private Videoprojecteur videoprojecteur;

	@JsonView(JsonViews.SalleAvecFormations.class)
	@OneToMany(mappedBy = "salle")
	private List<Formation> formations;
	
	public int getCapacite() {
		return capacite;
	}

	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}

	public Videoprojecteur getVideoprojecteur() {
		return videoprojecteur;
	}

	public void setVideoprojecteur(Videoprojecteur videoprojecteur) {
		this.videoprojecteur = videoprojecteur;
	}

}
