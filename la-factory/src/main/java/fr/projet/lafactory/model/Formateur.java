package fr.projet.lafactory.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import fr.projet.lafactory.model.view.JsonViews;

@Entity
@Table(name = "Formateur")
//@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "FOR_ID")),
//		@AttributeOverride(name = "nom", column = @Column(name = "FOR_NOM")),
//		@AttributeOverride(name = "prenom", column = @Column(name = "FOR_PRENOM")),
//		@AttributeOverride(name = "adresse", column = @Column(name = "FOR_ADRESSE")),
//		@AttributeOverride(name = "email", column = @Column(name = "FOR_EMAIL")),
//		@AttributeOverride(name = "telephone", column = @Column(name = "FOR_TELEPHONE")),
//		@AttributeOverride(name = "motDePasse", column = @Column(name = "FOR_MOTDEPASSE")),
//		@AttributeOverride(name = "version", column = @Column(name = "FOR_VERSION")) })
public class Formateur extends Personne {

	@JsonView(JsonViews.Formateur.class)
	@OneToMany(mappedBy = "formateur")
	private List<Enseignement> enseignements;

	@JsonView(JsonViews.Formateur.class)
	@OneToMany(mappedBy = "formateur")
	private List<Indisponibilite> indisponibilites;
	
	
	public List<Enseignement> getEnseignements() {
		return enseignements;
	}

	public void setEnseignements(List<Enseignement> enseignements) {
		this.enseignements = enseignements;
	}

	
	
}
