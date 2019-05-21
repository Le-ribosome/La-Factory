package fr.projet.lafactory.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.projet.lafactory.model.Personne;

public interface IDAOPersonne extends JpaRepository<Personne, Integer>{

}
