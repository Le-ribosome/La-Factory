package fr.projet.lafactory.restcontroller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonView;

import fr.projet.lafactory.dao.IDAOFormateur;
import fr.projet.lafactory.dao.IDAOGestionnaire;
import fr.projet.lafactory.dao.IDAOPersonne;
import fr.projet.lafactory.dao.IDAOStagiaire;
import fr.projet.lafactory.dao.IDAOTechnicien;
import fr.projet.lafactory.model.Formateur;
import fr.projet.lafactory.model.Gestionnaire;
import fr.projet.lafactory.model.Personne;
import fr.projet.lafactory.model.Stagiaire;
import fr.projet.lafactory.model.Technicien;
import fr.projet.lafactory.model.view.JsonViews;

@RestController
@RequestMapping("/rest/user")
@CrossOrigin(origins = "*")
public class UserRestController {

	@Autowired
	private IDAOPersonne daoPersonne;
	
	@Autowired
	private IDAOFormateur daoFormateur;
	
	@Autowired
	private IDAOGestionnaire daoGestionnaire;
	
	@Autowired
	private IDAOStagiaire daoStagiaire;
	
	@Autowired
	private IDAOTechnicien daoTechnicien;
	
//CREATE pour: 
	
	//Ajout formateur: 
	@PostMapping(value= {"/formateur"})
	public ResponseEntity<Void> insert(@Valid @RequestBody Formateur formateur, BindingResult br, UriComponentsBuilder uCB) {
		if(br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		daoFormateur.save(formateur);
		//Ça nous donne une uri, important car dans une reponse on a un body mais aussi un header
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uCB.path("/rest/formateur/{id}").buildAndExpand(formateur.getId()).toUri());
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	//Ajout gestionnaire: 
		@PostMapping(value= {"/gestionnaire"})
		public ResponseEntity<Void> insert(@Valid @RequestBody Gestionnaire gestionnaire, BindingResult br, UriComponentsBuilder uCB) {
			if(br.hasErrors()) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			daoGestionnaire.save(gestionnaire);
			//Ça nous donne une uri, important car dans une reponse on a un body mais aussi un header
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(uCB.path("/rest/gestionnaire/{id}").buildAndExpand(gestionnaire.getId()).toUri());
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		
		//Ajout stagiaire: 
		@PostMapping(value= {"/stagiaire"})
		public ResponseEntity<Void> insert(@Valid @RequestBody Stagiaire stagiaire, BindingResult br, UriComponentsBuilder uCB) {
			if(br.hasErrors()) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			daoStagiaire.save(stagiaire);
			//Ça nous donne une uri, important car dans une reponse on a un body mais aussi un header
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(uCB.path("/rest/stagiaire/{id}").buildAndExpand(stagiaire.getId()).toUri());
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		
		//Ajout technicien: 
				@PostMapping(value= {"/technicien"})
				public ResponseEntity<Void> insert(@Valid @RequestBody Technicien technicien, BindingResult br, UriComponentsBuilder uCB) {
					if(br.hasErrors()) {
						return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
					}
					daoTechnicien.save(technicien);
					//Ça nous donne une uri, important car dans une reponse on a un body mais aussi un header
					HttpHeaders headers = new HttpHeaders();
					headers.setLocation(uCB.path("/rest/technicien/{id}").buildAndExpand(technicien.getId()).toUri());
					return new ResponseEntity<>(HttpStatus.CREATED);
				}
	
//	READ pour:
	
	//Tout le monde: personne
	@JsonView(JsonViews.User.class)
	@GetMapping(value = {"","/"})
	public ResponseEntity<List<Personne>> findAll() {
		return new ResponseEntity<List<Personne>>(daoPersonne.findAll(), HttpStatus.OK);
	}
	
	//Formateurs
	@JsonView(JsonViews.User.class)
	@GetMapping(value = {"/formateurs"})
	public ResponseEntity<List<Formateur>> findAllFormateurs() {
		return new ResponseEntity<List<Formateur>>(daoFormateur.findAll(), HttpStatus.OK);
	}
	
	//Gestionnaires
	@JsonView(JsonViews.User.class)
	@GetMapping(value = {"/gestionnaires"})
	public ResponseEntity<List<Gestionnaire>> findAllGestionnaires() {
		return new ResponseEntity<List<Gestionnaire>>(daoGestionnaire.findAll(), HttpStatus.OK);
	}
	
	//Stagiaires
	@JsonView(JsonViews.User.class)
	@GetMapping(value = {"/stagiaires"})
	public ResponseEntity<List<Stagiaire>> findAllStagiaires() {
		return new ResponseEntity<List<Stagiaire>>(daoStagiaire.findAll(), HttpStatus.OK);
	}
	
	//Techniciens
	@JsonView(JsonViews.User.class)
	@GetMapping(value = {"/techniciens"})
	public ResponseEntity<List<Technicien>> findAllTechniciens() {
		return new ResponseEntity<List<Technicien>>(daoTechnicien.findAll(), HttpStatus.OK);
	}



}
