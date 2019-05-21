package fr.projet.lafactory.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.projet.lafactory.model.Enseignement;

public interface IDAOEnseignement extends JpaRepository<Enseignement, Integer> {

}
