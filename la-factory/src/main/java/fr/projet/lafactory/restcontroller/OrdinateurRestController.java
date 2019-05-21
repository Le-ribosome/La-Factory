package fr.projet.lafactory.restcontroller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonView;

import fr.projet.lafactory.dao.IDAOOrdinateur;
import fr.projet.lafactory.model.Matiere;
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
		
		// Donne la liste des stagiaires à qui sont confiés les ordinateurs
		@JsonView(JsonViews.OrdinateurAvecStagiaire.class)
		@GetMapping("/stagiaire")
		public List<Ordinateur> findAllWithOrdinateurs() { 
			return daoOrdinateur.findAll();
		}
		
		// Pour ajouter un ordinateur
		@PostMapping(value = { "", "/" })
		public ResponseEntity<Void> insert(@Valid @RequestBody Ordinateur ordinateur, BindingResult br,
				UriComponentsBuilder uCB) {
			if (br.hasErrors()) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			daoOrdinateur.save(ordinateur);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(uCB.path("/rest/ordinateur/{id}").buildAndExpand(ordinateur.getId()).toUri());
			return new ResponseEntity<>(headers, HttpStatus.CREATED);
		}
		
		// cherche les stagiaires accrédités a cet ordinateur
		@JsonView(JsonViews.OrdinateurAvecStagiaire.class)
		@GetMapping("/{id}/stagiaire")
		public ResponseEntity<Ordinateur> findByIdWithSatgiaire(@PathVariable(name = "id") Integer id) {
			Optional<Ordinateur> opt = daoOrdinateur.findById(id);
			if (opt.isPresent()) {
				return new ResponseEntity<Ordinateur>(opt.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		
		// affiche l'ordinateur
		 @JsonView(JsonViews.Ordinateur.class)
		    @GetMapping("/{id}")
		    public ResponseEntity<Ordinateur> findById(@PathVariable(name="id") Integer id){
		        Optional<Ordinateur> opt = daoOrdinateur.findById(id);
		        if (opt.isPresent()) {
		            return new ResponseEntity<Ordinateur>(opt.get(), HttpStatus.OK);
		        }
		        else {
		            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		        }
		    }
		 // Pour modifier l'ordinateur, et suivre le numéro de sa version
		 @PutMapping("/{id}")
			public ResponseEntity<Void> update(@PathVariable(name="id") Integer id,@Valid @RequestBody Ordinateur ordinateur, BindingResult br) {
					if(br.hasErrors()) {
						return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
					}
					Optional<Ordinateur> opt = daoOrdinateur.findById(id);
					if (opt.isPresent()) {
						ordinateur.setId(id); 
						ordinateur.setVersion(opt.get().getVersion());
						daoOrdinateur.save(ordinateur);
						return new ResponseEntity<>(HttpStatus.OK);
					} else {
						return new ResponseEntity<>(HttpStatus.NOT_FOUND);
					}
					
				}
		 // supprimer un ordinateur
		 @DeleteMapping("/{id}")
			public ResponseEntity<Void> delete(@PathVariable(name="id") Integer id) {
				Optional<Ordinateur> opt = daoOrdinateur.findById(id);
				if (opt.isPresent()) {
					daoOrdinateur.deleteById(id);
					return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
				} else {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
				
			}
	}


