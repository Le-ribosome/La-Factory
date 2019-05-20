package fr.projet.lafactory.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Stagiaire")
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "STA_ID")),
	@AttributeOverride(name = "nom", column = @Column(name = "STA_NOM")),
	@AttributeOverride(name = "prenom", column = @Column(name = "STA_PRENOM")),
	@AttributeOverride(name = "adresse", column = @Column(name = "STA_ADRESSE")),
	@AttributeOverride(name = "email", column = @Column(name = "STA_ADRESSE")),
	@AttributeOverride(name = "telephone", column = @Column(name = "STA_TELEPHONE")),
	@AttributeOverride(name = "motDePasse", column = @Column(name = "STA_MOTDEPASSE")),
	@AttributeOverride(name = "version", column = @Column(name = "STA_VERSION")) })
public class Stagiaire extends Personne {

}
