package fr.projet.lafactory.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="ordinateur")
@AttributeOverrides({
	@AttributeOverride(name="id", column=@Column(name="ORD_ID")),
	@AttributeOverride(name="code", column=@Column(name="ORD_CODE")),
	@AttributeOverride(name="coutJournee", column=@Column(name="ORD_COUTJOURNEE"))
})
public class Ordinateur extends Materiel{
	
	private String processeur;
	
	private int quantiteRAM;
	
	private int quantitedd;
	
	private int anneeAchat;
	
	private Stagiaire proprietaire;
}
