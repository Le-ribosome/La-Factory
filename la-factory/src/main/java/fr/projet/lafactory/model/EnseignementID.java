package fr.projet.lafactory.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class EnseignementID implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name = "ENS_FORMATEUR_ID")
	private int idFormateur;

	@Column(name = "ENS_MATIERE_ID")
	private int idMatiere;

}
