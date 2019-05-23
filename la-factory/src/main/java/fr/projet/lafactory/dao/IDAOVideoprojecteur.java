package fr.projet.lafactory.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.projet.lafactory.model.Videoprojecteur;

public interface IDAOVideoprojecteur extends JpaRepository<Videoprojecteur, Integer> {

	@Query("select v from Videoprojecteur v left join fetch v.salle where v.salle.id is NULL")
	public List<Videoprojecteur> findAllSansSalle();
	
}
