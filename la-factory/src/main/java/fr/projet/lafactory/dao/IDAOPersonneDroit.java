package fr.projet.lafactory.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.projet.lafactory.model.PersonneDroit;

public interface IDAOPersonneDroit extends JpaRepository<PersonneDroit, Integer>{
	
}
