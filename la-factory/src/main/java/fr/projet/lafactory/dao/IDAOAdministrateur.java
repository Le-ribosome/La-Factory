package fr.projet.lafactory.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.projet.lafactory.model.Administrateur;

public interface IDAOAdministrateur extends JpaRepository<Administrateur, Integer> {

}
