package fr.projet.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.projet.lafactory.dao.IDAOMatiere;
import fr.projet.lafactory.dao.IDAOOrdinateur;

@RestController
@RequestMapping("/rest/ordinateur")
@CrossOrigin(origins= "*")
public class OrdinateurRestController {

	@Autowired
	private IDAOOrdinateur daoOrdinateur; 
	
	
}
