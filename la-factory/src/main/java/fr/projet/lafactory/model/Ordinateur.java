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


@Entity
@Table(name="Ordinateur")
@AttributeOverrides({
	@AttributeOverride(name="id", column=@Column(name="ORD_ID")),
	@AttributeOverride(name="code", column=@Column(name="ORD_CODE")),
	@AttributeOverride(name="coutJournee", column=@Column(name="ORD_COUTJOURNEE"))
})
public class Ordinateur extends Materiel{
	
	@Column(name="ORD_PROCESSEUR", columnDefinition="VARCHAR(100) NOT NULL")
	@NotEmpty
	@NotNull
	private String processeur;
	
	@Column(name="ORD_QUANTITERAM")
	@NotNull
	private int quantiteRAM;
	
	@Column(name="ORD_QUANTITEDD")
	@NotNull
	private int quantitedd;
	
	@Column(name="ORD_ANNEEACHAT")
	@NotNull
	private int anneeAchat;

	@OneToMany(mappedBy="ordinateur")
	private List<Stagiaire> stagiaires;
}
