package fr.projet.lafactory.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.projet.lafactory.model.Formateur;

public interface IDAOFormateur extends JpaRepository<Formateur, Integer> {

}
