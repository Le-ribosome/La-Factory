package fr.projet.lafactory.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="salle")
@AttributeOverrides({
	@AttributeOverride(name="id", column=@Column(name="SAL_ID")),
	@AttributeOverride(name="code", column=@Column(name="SAL_CODE")),
	@AttributeOverride(name="coutJournee", column=@Column(name="SAL_COUTJOURNEE"))
})
public class Salle extends Materiel{
	
	@Column(name="SAL_CAPACITE")
	@NotNull
	private int capacite;
	
	
	@JoinColumn(name="SAL_VIDEOPROJECTEUR_ID")
	@OneToOne(mappedBy="videoprojecteur")
	private Videoprojecteur videoprojecteur;

}
