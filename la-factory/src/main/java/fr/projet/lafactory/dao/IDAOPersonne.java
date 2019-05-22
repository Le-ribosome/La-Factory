package fr.projet.lafactory.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.projet.lafactory.model.Personne;

public interface IDAOPersonne extends JpaRepository<Personne, Integer>{
	@Query("select p from Personne p left join fetch p.droits where p.email=?1")
	public Optional<Personne> findByIdWithDroits(String email);
	
	public Optional<Personne> findByEmail(String email);
}
