package fr.projet.lafactory.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Personne")
public class Personne {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "PER_ID")
	private int id;

	@Column(name = "PER_NOM")
	@NotNull
	private String nom;
	
	@Column(name = "PER_PRENOM")
	@NotNull
	private String prenom;
	
	@Column(name = "PER_ADRESSE")
	@NotNull
	private String adresse;

	@Column(name = "PER_EMAIL")
	@NotNull
	private String email;
	
	@Column(name = "PER_TELEPHONE")
	@NotNull
	private String telephone;
	
	@Column(name = "PER_MOTDEPASSE")
	@NotNull
	private String motDePasse;

	@OneToMany(mappedBy="personne")
	private List<PersonneDroit> droits;
	
	
	
	
	
}
