package fr.projet.lafactory.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.projet.lafactory.model.Formation;

public interface IDAOFormation extends JpaRepository<Formation, Integer> {

}
