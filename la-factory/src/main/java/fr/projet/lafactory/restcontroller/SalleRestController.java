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

import fr.projet.lafactory.dao.IDAOSalle;
import fr.projet.lafactory.model.Salle;
import fr.projet.lafactory.model.view.JsonViews; 
 
 
@RestController 
@RequestMapping("/rest/salle") 
@CrossOrigin(origins = "*") 
public class SalleRestController { 
	 
	@Autowired 
	private IDAOSalle daoSalle; 
	 
		@GetMapping(value = { "", "/" })
		@JsonView(JsonViews.Salle.class)
		public ResponseEntity<List<Salle>> findAll() {
			return new ResponseEntity<List<Salle>>(daoSalle.findAll(), HttpStatus.OK);
		}
		
} 
