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

import com.fasterxml.jackson.annotation.JsonView;

import fr.projet.lafactory.model.view.JsonViews;

@Entity
@Table(name = "Formation")
public class Formation {

	@JsonView(JsonViews.Formation.class)
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "FMT_ID")
	private int id;
	
	@JsonView(JsonViews.Formation.class)
	@Column(name = "FMT_TITRE")
	@NotNull
	private String titre;

	@JsonView(JsonViews.Formation.class)
	@Column(name = "FMT_DATEDEBUT")
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date dateDebut;

	@JsonView(JsonViews.Formation.class)
	@Column(name = "FMT_DATEFIN")
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date dateFin;

	@Column(name="FMT_VERSION")
	@Version
	private int version;

	@JsonView(JsonViews.FormationAvecGestionnaire.class)
	@ManyToOne()
	@JoinColumn(name = "FMT_GESTIONNAIRE_ID", nullable = true)
	private Gestionnaire gestionnaire;

	@JsonView(JsonViews.FormationAvecSalle.class)
	//@JsonView({JsonViews.FormationAvecSalle.class, JsonViews.FormationAvecModuleEtSalle.class})
	@ManyToOne()
	@JoinColumn(name = "FMT_SALLE_ID", nullable = true)
	private Salle salle;

	@JsonView(JsonViews.FormationAvecModule.class)
	//@JsonView({JsonViews.FormationAvecModule.class, JsonViews.FormationAvecModuleEtSalle.class})
	@OneToMany(mappedBy = "formation")
	private List<Module> modules;

	@JsonView(JsonViews.FormationAvecStagiaire.class)
	@OneToMany(mappedBy = "formation")
	private List<Stagiaire> stagiaires;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Gestionnaire getGestionnaire() {
		return gestionnaire;
	}

	public void setGestionnaire(Gestionnaire gestionnaire) {
		this.gestionnaire = gestionnaire;
	}

	public Salle getSalle() {
		return salle;
	}

	public void setSalle(Salle salle) {
		this.salle = salle;
	}

	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

	public List<Stagiaire> getStagiaires() {
		return stagiaires;
	}

	public void setStagiaires(List<Stagiaire> stagiaires) {
		this.stagiaires = stagiaires;
	}
	
	
}
