package fr.projet.lafactory.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.projet.lafactory.model.Materiel;

public interface IDAOMateriel extends JpaRepository<Materiel, Integer> {

}
