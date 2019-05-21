package fr.projet.lafactory.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Version;


@Embeddable
public class EnseignementID implements Serializable {
	

	private static final long serialVersionUID = 1L;

	@Column(name = "ENS_FORMATEUR_ID")
	private int idFormateur;

	@Column(name = "ENS_MATIERE_ID")
	private int idMatiere;

	public int getIdFormateur() {
		return idFormateur;
	}

	public void setIdFormateur(int idFormateur) {
		this.idFormateur = idFormateur;
	}

	public int getIdMatiere() {
		return idMatiere;
	}

	public void setIdMatiere(int idMatiere) {
		this.idMatiere = idMatiere;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}
