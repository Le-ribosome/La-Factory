package fr.projet.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import fr.projet.lafactory.dao.IDAOPersonne;
import fr.projet.lafactory.model.Personne;
import fr.projet.lafactory.model.view.JsonViews;

@RestController
@RequestMapping("/rest/user")
@CrossOrigin(origins = "*")
public class UserRestController {

	@Autowired
	private IDAOPersonne daoPersonne;
	
	@JsonView(JsonViews.User.class)
	@GetMapping(value = {"","/"})
	public ResponseEntity<List<Personne>> findAll() {
		return new ResponseEntity<List<Personne>>(daoPersonne.findAll(), HttpStatus.OK);
	}
}
