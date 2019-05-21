package fr.projet.lafactory.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "Enseignement")
public class Enseignement {

	@EmbeddedId
	private EnseignementID id;

	@ManyToOne()
    @MapsId("ENS_FORMATEUR_ID")
	@JoinColumn(name = "ENS_FORMATEUR_ID")
	private Formateur formateur;

	@ManyToOne()
    @MapsId("ENS_MATIERE_ID")
	@JoinColumn(name = "ENS_MATIERE_ID")
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
