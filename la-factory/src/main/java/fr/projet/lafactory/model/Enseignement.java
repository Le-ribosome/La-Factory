package fr.projet.lafactory.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonView;

import fr.projet.lafactory.model.view.JsonViews;

@Entity
@Table(name = "Enseignement")
public class Enseignement {

	@Version
	private int version;
	
	@EmbeddedId
	private EnseignementID id;

	@JsonView(JsonViews.EnseignementAvecFormateur.class)
	@ManyToOne()
    @MapsId("ENS_FORMATEUR_ID")
	@JoinColumn(name = "ENS_FORMATEUR_ID", insertable = false, updatable = false)
	private Formateur formateur;

	@JsonView(JsonViews.EnseignementAvecMatiere.class)
	@ManyToOne()
    @MapsId("ENS_MATIERE_ID")
	@JoinColumn(name = "ENS_MATIERE_ID", insertable = false, updatable = false)
	private Matiere matiere;

	public EnseignementID getId() {
		return id;
	}

	public void setId(EnseignementID id) {
		this.id = id;
	}

	public Formateur getFormateur() {
		return formateur;
	}

	public void setFormateur(Formateur formateur) {
		this.formateur = formateur;
	}

	public Matiere getMatiere() {
		return matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}
	
	

}
