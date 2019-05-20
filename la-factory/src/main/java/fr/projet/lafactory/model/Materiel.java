package fr.projet.lafactory.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public class Materiel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	private String code;
	
	@NotNull
	private float coutJournee;
	
	private boolean estDisponible(Date dateDebut, Date dateFin) {
		boolean estDispo = true;
		return estDispo;
	}
}
