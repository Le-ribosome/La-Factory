package fr.projet.lafactory.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.projet.lafactory.model.Salle;

public interface IDAOSalle extends JpaRepository<Salle, Integer> {

}
