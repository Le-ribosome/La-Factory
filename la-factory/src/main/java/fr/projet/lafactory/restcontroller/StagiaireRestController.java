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

import fr.projet.lafactory.dao.IDAOStagiaire;
import fr.projet.lafactory.model.Stagiaire;
import fr.projet.lafactory.model.view.JsonViews;

@RestController
@RequestMapping("/rest/stagiaire")
@CrossOrigin(origins = "*")
public class StagiaireRestController {

	@Autowired
	private IDAOStagiaire daoStagiaire;
	
	@JsonView(JsonViews.User.class)
	@GetMapping(value = {"","/"})
	public ResponseEntity<List<Stagiaire>> findAll() {
		return new ResponseEntity<List<Stagiaire>>(daoStagiaire.findAll(), HttpStatus.OK);
	}
	
	
}
