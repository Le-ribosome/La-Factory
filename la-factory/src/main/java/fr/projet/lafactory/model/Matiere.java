package fr.projet.lafactory.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Matiere")
public class Matiere {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "MAT_ID")
	private int id;

	@Column(name = "MAT_NOM")
	@NotNull
	private String nom;

	@Column(name = "MAT_OBJECTIF")
	@NotNull
	private String objectif;

	@Column(name = "MAT_PREREQUIS")
	private String prerequis;
	
	@Column(name = "MAT_CONTENU")
	private String contenu;
	
	@Column(name="MAT_NBJOURS")
	private int nbJours;

	@Column(name="MAT_VERSION")
	@Version
	private int version;

	@OneToMany(mappedBy="matiere")
	private List<Enseignement> enseignements;

	
}
