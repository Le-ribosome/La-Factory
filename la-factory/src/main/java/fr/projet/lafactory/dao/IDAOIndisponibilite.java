package fr.projet.lafactory.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.projet.lafactory.model.Indisponibilite;

public interface IDAOIndisponibilite extends JpaRepository<Indisponibilite, Integer> {

}
