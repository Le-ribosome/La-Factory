package fr.projet.lafactory.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonView;

import fr.projet.lafactory.model.view.JsonViews;

@Entity
@Table(name = "Personne")
@Inheritance(strategy= InheritanceType.JOINED)
public class Personne {

	@JsonView(JsonViews.User.class)
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="PER_ID")
	private int id;

	@JsonView(JsonViews.User.class)
	@Column(name="PER_NOM")
	private String nom;

	@JsonView(JsonViews.User.class)
	@Column(name="PER_PRENOM")
	private String prenom;

	@JsonView(JsonViews.User.class)
	@Column(name="PER_ADRESSE")
	private String adresse;

	@JsonView(JsonViews.User.class)
	@Column(name="PER_EMAIL")
	@NotNull
	private String email;

	@JsonView(JsonViews.User.class)
	@Column(name="PER_TELEPHONE")
	private String telephone;

	@JsonView(JsonViews.User.class)
	@Column(name="PER_MOTDEPASSE")
	@NotNull
	private String motDePasse;

	@JsonView(JsonViews.User.class)
	@Column(name="PER_VERSION")
	@Version
	private int version;
	
	@JsonView(JsonViews.User.class)
	@OneToMany(mappedBy="personne")
	private List<PersonneDroit> droits;

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

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public List<PersonneDroit> getDroits() {
		return droits;
	}

	public void setDroits(List<PersonneDroit> droits) {
		this.droits = droits;
	}
	
	
}
