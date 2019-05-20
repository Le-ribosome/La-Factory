package fr.projet.lafactory.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.projet.lafactory.model.Gestionnaire;

public interface IDAOGestionnaire extends JpaRepository<Gestionnaire, Integer> {

}
