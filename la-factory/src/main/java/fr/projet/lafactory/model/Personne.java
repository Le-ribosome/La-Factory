package fr.projet.lafactory.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public class Personne {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;

	@NotNull
	private String nom;
	
	@NotNull
	private String prenom;
	
	@NotNull
	private String adresse;

	@NotNull
	private String email;
	
	@NotNull
	private String telephone;
	
	@NotNull
	private String motDePasse;

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
