package fr.projet.lafactory.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Materiel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String code;
	
	private float coutJournee;
	
	private boolean estDisponible(Date dateDebut, Date dateFin) {
		boolean estDispo = true;
		return estDispo;
	}
}
