package fr.projet.lafactory.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Stagiaire")
//@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "STA_ID")),
//	@AttributeOverride(name = "nom", column = @Column(name = "STA_NOM")),
//	@AttributeOverride(name = "prenom", column = @Column(name = "STA_PRENOM")),
//	@AttributeOverride(name = "adresse", column = @Column(name = "STA_ADRESSE")),
//	@AttributeOverride(name = "email", column = @Column(name = "STA_ADRESSE")),
//	@AttributeOverride(name = "telephone", column = @Column(name = "STA_TELEPHONE")),
//	@AttributeOverride(name = "motDePasse", column = @Column(name = "STA_MOTDEPASSE")),
//	@AttributeOverride(name = "version", column = @Column(name = "STA_VERSION")) })
public class Stagiaire extends Personne {

	@ManyToOne()
	@JoinColumn(name = "STA_FORMATION_ID", nullable = true)
	private Formation formation;

	@ManyToOne()
	@JoinColumn(name = "STA_ORDINATEUR_ID", nullable = true)
	private Ordinateur ordinateur;

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

	public Ordinateur getOrdinateur() {
		return ordinateur;
	}

	public void setOrdinateur(Ordinateur ordinateur) {
		this.ordinateur = ordinateur;
	}

	
}
