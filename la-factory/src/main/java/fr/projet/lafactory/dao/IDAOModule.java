package fr.projet.lafactory.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.projet.lafactory.model.Module;

public interface IDAOModule extends JpaRepository<Module, Integer>{

}
