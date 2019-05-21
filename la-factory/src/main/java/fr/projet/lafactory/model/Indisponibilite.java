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

import com.fasterxml.jackson.annotation.JsonView;

import fr.projet.lafactory.model.view.JsonViews;

@Entity
@Table(name="Indisponibilite")
public class Indisponibilite {

	@Version
	private int version;
	
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@JsonView(JsonViews.Indisponibilite.class)
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "IND_ID")
	private int id;

	@JsonView(JsonViews.Formateur.class)
	@Column(name = "IND_DATEDEBUT")
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date dateDebut;

	@JsonView(JsonViews.Formateur.class)
	@Column(name = "IND_DATEFIN")
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date dateFin;
	
	@JsonView(JsonViews.IndisponibiliteAvecFormateur.class)
	@ManyToOne()
	@JoinColumn(name = "IND_FORMATEUR_ID", nullable = true)
	private Formateur formateur;

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

	public Formateur getFormateur() {
		return formateur;
	}

	public void setFormateur(Formateur formateur) {
		this.formateur = formateur;
	}
	
}
