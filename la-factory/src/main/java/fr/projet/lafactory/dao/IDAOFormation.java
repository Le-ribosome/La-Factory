package fr.projet.lafactory.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.projet.lafactory.model.Formation;

public interface IDAOFormation extends JpaRepository<Formation, Integer> {

	public Optional<Formation> findFormationByTitre(String titre);
}
