package fr.projet.lafactory.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Technicien")
//@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "TEC_ID")),
//	@AttributeOverride(name = "nom", column = @Column(name = "TEC_NOM")),
//	@AttributeOverride(name = "prenom", column = @Column(name = "TEC_PRENOM")),
//	@AttributeOverride(name = "adresse", column = @Column(name = "TEC_ADRESSE")),
//	@AttributeOverride(name = "email", column = @Column(name = "TEC_ADRESSE")),
//	@AttributeOverride(name = "telephone", column = @Column(name = "TEC_TELEPHONE")),
//	@AttributeOverride(name = "motDePasse", column = @Column(name = "TEC_MOTDEPASSE")),
//	@AttributeOverride(name = "version", column = @Column(name = "TEC_VERSION")) })
public class Technicien extends Personne {

}
