package fr.projet.lafactory.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Formation")
public class Formation {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "FMT_ID")
	private int id;

	@Column(name = "FMT_DATEDEBUT")
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date dateDebut;

	@Column(name = "FMT_DATEFIN")
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date dateFin;

	@Column(name="FMT_VERSION")
	@Version
	private int version;

	@ManyToOne()
	@JoinColumn(name = "FMT_GESTIONNAIRE_ID", nullable = true)
	private Gestionnaire gestionnaire;

	@ManyToOne()
	@JoinColumn(name = "FMT_SALLE_ID", nullable = true)
	private Salle salle;

	@OneToMany(mappedBy = "formation")
	private List<Module> modules;

	@OneToMany(mappedBy = "formation")
	private List<Stagiaire> stagiaires;
}
