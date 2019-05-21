package fr.projet.lafactory.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import fr.projet.lafactory.dao.IDAOOrdinateur;
import fr.projet.lafactory.model.Ordinateur;
import fr.projet.lafactory.model.view.JsonViews;

@RestController
@RequestMapping("/rest/ordinateur")
@CrossOrigin(origins= "*")
public class OrdinateurRestController {

	@Autowired
	private IDAOOrdinateur daoOrdinateur; 
	
	// donne la liste des ordinateurs
		@GetMapping(value = { "", "/" })
		@JsonView(JsonViews.Ordinateur.class)
		public ResponseEntity<List<Ordinateur>> findAll() {
			return new ResponseEntity<List<Ordinateur>>(daoOrdinateur.findAll(), HttpStatus.OK);
		}
		
	
}
