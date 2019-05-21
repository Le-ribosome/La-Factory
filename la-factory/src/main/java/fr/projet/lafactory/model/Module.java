package fr.projet.lafactory.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Module")
public class Module {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "MOD_ID")
	private int id;

	@Column(name = "MOD_DATEDEBUT")
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date dateDebut;

	@Column(name="MOD_VERSION")
	@Version
	private int version;


	@ManyToOne()
	@JoinColumn(name = "MOD_MATIERE_ID", nullable = true)
	private Matiere matiere;

	@ManyToOne()
	@JoinColumn(name = "MOD_FORMATEUR_ID", nullable = true)
	private Formateur formateur;

	@ManyToOne()
	@JoinColumn(name = "MOD_FORMATION_ID", nullable = true)
	private Formation formation;

	
}
