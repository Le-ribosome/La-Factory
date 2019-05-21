package fr.projet.lafactory.model;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonView;

import fr.projet.lafactory.model.view.JsonViews;


@Entity
@Table(name="Ordinateur")
@AttributeOverrides({
	@AttributeOverride(name="id", column=@Column(name="ORD_ID")),
	@AttributeOverride(name="version", column=@Column(name="ORD_VERSION")),
	@AttributeOverride(name="code", column=@Column(name="ORD_CODE")),
	@AttributeOverride(name="coutJournee", column=@Column(name="ORD_COUTJOURNEE"))
})
public class Ordinateur extends Materiel{
	
	@JsonView(JsonViews.Ordinateur.class)
	@Column(name="ORD_PROCESSEUR", columnDefinition="VARCHAR(100) NOT NULL")
	private String processeur = "Indiquez le processeur";
	
	@JsonView(JsonViews.Ordinateur.class)
	@Column(name="ORD_QUANTITERAM")
	private int quantiteRAM = 2;
	
	@JsonView(JsonViews.Ordinateur.class)
	@Column(name="ORD_QUANTITEDD")
	private int quantiteDD = 500;
	
	@JsonView(JsonViews.Ordinateur.class)
	@Column(name="ORD_ANNEEACHAT")
	private int anneeAchat = 2019;

	@JsonView(JsonViews.OrdinateurAvecStagiaire.class)
	@OneToMany(mappedBy="ordinateur")
	private List<Stagiaire> stagiaires;

	public String getProcesseur() {
		return processeur;
	}

	public void setProcesseur(String processeur) {
		this.processeur = processeur;
	}

	public int getQuantiteRAM() {
		return quantiteRAM;
	}

	public void setQuantiteRAM(int quantiteRAM) {
		this.quantiteRAM = quantiteRAM;
	}

	public int getQuantiteDD() {
		return quantiteDD;
	}

	public void setQuantiteDD(int quantiteDD) {
		this.quantiteDD = quantiteDD;
	}

	public int getAnneeAchat() {
		return anneeAchat;
	}

	public void setAnneeAchat(int anneeAchat) {
		this.anneeAchat = anneeAchat;
	}

	public List<Stagiaire> getStagiaires() {
		return stagiaires;
	}

	public void setStagiaires(List<Stagiaire> stagiaires) {
		this.stagiaires = stagiaires;
	}
	
	
	
}
