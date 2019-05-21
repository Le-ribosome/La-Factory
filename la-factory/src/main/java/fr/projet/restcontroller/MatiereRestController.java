package fr.projet.restcontroller;

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

import fr.formation.sopra.sopraSpringBoot.model.Produit;
import fr.projet.lafactory.dao.IDAOMatiere;
import fr.projet.lafactory.model.Matiere;

@RestController
@RequestMapping("/rest/matiere")
@CrossOrigin(origins= "*")
public class MatiereRestController {

	@Autowired
	private IDAOMatiere daoMatiere; 
	
	@GetMapping(value = { "", "/" })
	@JsonView(JsonViews.Matiere.class)
	public ResponseEntity<List<Matiere>> findAll() {
		return new ResponseEntity<List<Matiere>>(daoMatiere.findAll(), HttpStatus.OK);
	}
	
	// Donne la liste des formateurs accrédités pour chaque matière
	@JsonView(JsonViews.MatiereAvecEnseignement.class)
	@GetMapping("/formateur")
	public List<Matiere> findAllWithMatieres() { 
		return daoMatiere.findAll();
	}
	
	@PostMapping(value = { "", "/" })
	public ResponseEntity<Void> insert(@Valid @RequestBody Matiere matiere, BindingResult br,
			UriComponentsBuilder uCB) {
		if (br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		daoMatiere.save(matiere);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uCB.path("/rest/matiere/{id}").buildAndExpand(matiere.getId()).toUri());
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}
	
	@JsonView(JsonViews.MatiereAvecEnseignement.class)
	@GetMapping("/{id}/formateur")
	public ResponseEntity<Matiere> findByIdWithFormateur(@PathVariable(name = "id") Integer id) {
		Optional<Matiere> opt = daoMatiere.findById(id);
		if (opt.isPresent()) {
			return new ResponseEntity<Matiere>(opt.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	 @JsonView(JsonViews.Matiere.class)
	    @GetMapping("/{id}")
	    public ResponseEntity<Matiere> findById(@PathVariable(name="id") Integer id){
	        Optional<Matiere> opt = daoMatiere.findById(id);
	        if (opt.isPresent()) {
	            return new ResponseEntity<Matiere>(opt.get(), HttpStatus.OK);
	        }
	        else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	 @PutMapping("/{id}")
		public ResponseEntity<Void> update(@PathVariable(name="id") Integer id,@Valid @RequestBody Matiere matiere, BindingResult br) {
				if(br.hasErrors()) {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
				Optional<Matiere> opt = daoMatiere.findById(id);
				if (opt.isPresent()) {
					matiere.setId(id); // si pas d'id il crée le produit
					matiere.setVersion(opt.get().getVersion());
					daoMatiere.save(matiere);
					return new ResponseEntity<>(HttpStatus.OK);
				} else {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
				
			}
	 
	 @DeleteMapping("/{id}")
		public ResponseEntity<Void> delete(@PathVariable(name="id") Integer id) {
			Optional<Matiere> opt = daoMatiere.findById(id);
			if (opt.isPresent()) {
				daoMatiere.deleteById(id);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
		}
}
