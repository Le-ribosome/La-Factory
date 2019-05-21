package fr.projet.lafactory.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Gestionnaire")
//@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "GES_ID")),
//	@AttributeOverride(name = "nom", column = @Column(name = "GES_NOM")),
//	@AttributeOverride(name = "prenom", column = @Column(name = "GES_PRENOM")),
//	@AttributeOverride(name = "adresse", column = @Column(name = "GES_ADRESSE")),
//	@AttributeOverride(name = "email", column = @Column(name = "GES_ADRESSE")),
//	@AttributeOverride(name = "telephone", column = @Column(name = "GES_TELEPHONE")),
//	@AttributeOverride(name = "motDePasse", column = @Column(name = "GES_MOTDEPASSE")),
//	@AttributeOverride(name = "version", column = @Column(name = "GES_VERSION")) })
public class Gestionnaire extends Personne {
	
	@OneToMany(mappedBy="gestionnaire")
	private List<Formation> formations;

}
