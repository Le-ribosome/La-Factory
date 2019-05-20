package fr.projet.lafactory.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.projet.lafactory.model.Technicien;

public interface IDAOTechnicien extends JpaRepository<Technicien, Integer> {

}
