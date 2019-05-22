package fr.projet.lafactory.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.projet.lafactory.model.Formation;
import fr.projet.lafactory.model.Module;

public interface IDAOModule extends JpaRepository<Module, Integer>{

	public List<Module> findAllByFormation(Formation formation);
	public List<Module> findAllByFormationId(int id);
	
}
