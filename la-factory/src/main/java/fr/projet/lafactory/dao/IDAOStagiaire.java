package fr.projet.lafactory.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.projet.lafactory.model.Stagiaire;

public interface IDAOStagiaire extends JpaRepository<Stagiaire, Integer>{
	
}
