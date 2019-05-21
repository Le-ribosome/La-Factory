package fr.projet.lafactory.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonView;

import fr.projet.lafactory.model.view.JsonViews;

@Entity
@Table(name = "Matiere")
public class Matiere {

	@JsonView(JsonViews.Matiere.class)
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "MAT_ID")
	private int id;

	@JsonView(JsonViews.Matiere.class)
	@Column(name = "MAT_NOM")
	@NotNull
	private String nom;
	
	@JsonView(JsonViews.Matiere.class)
	@Column(name = "MAT_NIVEAU")
	@NotNull
	private String niveau;

	@JsonView(JsonViews.Matiere.class)
	@Column(name = "MAT_OBJECTIF")
	private String objectif;

	@JsonView(JsonViews.Matiere.class)
	@Column(name = "MAT_PREREQUIS")
	private String prerequis;
	
	@JsonView(JsonViews.Matiere.class)
	@Column(name = "MAT_CONTENU")
	private String contenu;
	
	@JsonView(JsonViews.Matiere.class)
	@Column(name="MAT_NBJOURS")
	private int nbJours;


	@Column(name="MAT_VERSION")
	@Version
	private int version;

	@JsonView(JsonViews.MatiereAvecEnseignement.class)
	@OneToMany(mappedBy="matiere")
	private List<Enseignement> enseignements;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getObjectif() {
		return objectif;
	}

	public void setObjectif(String objectif) {
		this.objectif = objectif;
	}

	public String getPrerequis() {
		return prerequis;
	}

	public void setPrerequis(String prerequis) {
		this.prerequis = prerequis;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public int getNbJours() {
		return nbJours;
	}

	public void setNbJours(int nbJours) {
		this.nbJours = nbJours;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public List<Enseignement> getEnseignements() {
		return enseignements;
	}

	public void setEnseignements(List<Enseignement> enseignements) {
		this.enseignements = enseignements;
	}

	
	
	
}
