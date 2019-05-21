package fr.projet.lafactory.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonView;

import fr.projet.lafactory.model.view.JsonViews;

@MappedSuperclass
public class Materiel {

	@JsonView(JsonViews.Materiel.class)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	@Version
	private int version;
	
	@JsonView(JsonViews.Materiel.class)
	private String code = "MAT";
	
	@JsonView(JsonViews.Materiel.class)
	private float coutJournee = 0;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public float getCoutJournee() {
		return coutJournee;
	}

	public void setCoutJournee(float coutJournee) {
		this.coutJournee = coutJournee;
	}
	
	
}
