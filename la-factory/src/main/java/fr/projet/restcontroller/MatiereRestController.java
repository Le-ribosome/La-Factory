package fr.projet.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.projet.lafactory.dao.IDAOMatiere;

@RestController
@RequestMapping("/rest/matiere")
@CrossOrigin(origins= "*")
public class MatiereRestController {

	@Autowired
	private IDAOMatiere daoMatiere; 
	
	
	
}
