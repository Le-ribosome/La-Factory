package fr.projet.lafactory.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.projet.lafactory.model.Matiere;

public interface IDAOMatiere extends JpaRepository<Matiere, Integer>{

}
