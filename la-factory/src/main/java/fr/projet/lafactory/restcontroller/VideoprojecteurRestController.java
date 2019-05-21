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
import fr.projet.lafactory.dao.IDAOVideoprojecteur;
import fr.projet.lafactory.model.Ordinateur;
import fr.projet.lafactory.model.Videoprojecteur;
import fr.projet.lafactory.model.view.JsonViews;

@RestController 
@RequestMapping("/rest/videoprojecteur") 
@CrossOrigin(origins = "*") 
public class VideoprojecteurRestController {

	@Autowired
	private IDAOVideoprojecteur daoVideoprojecteur;
	
	// donne la liste des ordinateurs
		@GetMapping(value = { "", "/" })
		@JsonView(JsonViews.Videoprojecteur.class)
		public ResponseEntity<List<Videoprojecteur>> findAll() {
			return new ResponseEntity<List<Videoprojecteur>>(daoVideoprojecteur.findAll(), HttpStatus.OK);
		}
}
