package fr.projet.lafactory.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Enseignement")
public class Enseignement {

	@EmbeddedId
	private EnseignementID id;

	@ManyToOne()
	@JoinColumn(name = "ENS_FORMATEUR_ID")
	private Formateur formateur;

	@ManyToOne()
	@JoinColumn(name = "ENS_MATIERE_ID")
	private Matiere matiere;

}
