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

@Entity
@Table(name = "Personne")
@Inheritance(strategy= InheritanceType.JOINED)
public class Personne {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="PER_ID")
	private int id;

	@Column(name="PER_NOM")
	@NotNull
	private String nom;

	@Column(name="PER_PRENOM")
	@NotNull
	private String prenom;

	@Column(name="PER_ADRESSE")
	@NotNull
	private String adresse;

	@Column(name="PER_EMAIL")
	@NotNull
	private String email;

	@Column(name="PER_TELEPHONE")
	@NotNull
	private String telephone;

	@Column(name="PER_MOTDEPASSE")
	@NotNull
	private String motDePasse;

	@Column(name="PER_VERSION")
	@Version
	private int version;
	
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
