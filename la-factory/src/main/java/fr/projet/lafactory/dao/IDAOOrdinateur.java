package fr.projet.lafactory.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.projet.lafactory.model.Ordinateur;

public interface IDAOOrdinateur extends JpaRepository<Ordinateur, Integer> {
	
}
