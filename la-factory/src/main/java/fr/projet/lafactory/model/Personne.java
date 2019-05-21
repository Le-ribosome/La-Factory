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
	
	
}
