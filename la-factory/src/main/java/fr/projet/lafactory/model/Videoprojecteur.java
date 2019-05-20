package fr.projet.lafactory.model;


import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="videoprojecteur")
@AttributeOverrides({
	@AttributeOverride(name="id", column=@Column(name="VID_ID")),
	@AttributeOverride(name="code", column=@Column(name="VID_CODE")),
	@AttributeOverride(name="coutJournee", column=@Column(name="VID_COUTJOURNEE"))
})

public class Videoprojecteur extends Materiel {

}
