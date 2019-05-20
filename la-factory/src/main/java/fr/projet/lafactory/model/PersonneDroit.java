package fr.projet.lafactory.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "Personne_droit")
public class PersonneDroit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DRO_ID")
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Column(name = "DRO_DROIT")
	private Droit droit = Droit.DROIT_ALL;
	
	@ManyToOne
	@JoinColumn(name = "DRO_PERSONNE_ID")
	private Personne personne;
	
}
