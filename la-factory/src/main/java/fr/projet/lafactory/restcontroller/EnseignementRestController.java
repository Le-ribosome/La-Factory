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

import fr.projet.lafactory.dao.IDAOEnseignement;
import fr.projet.lafactory.model.Enseignement;
import fr.projet.lafactory.model.view.JsonViews;

@RestController
@RequestMapping("/rest/enseignement")
@CrossOrigin(origins = "*")
public class EnseignementRestController {

	@Autowired
	private IDAOEnseignement daoEnseignement;

	// --- READ ---
	@JsonView(JsonViews.User.class)
	@GetMapping(value = { "", "/" })
	public ResponseEntity<List<Enseignement>> findAll() {
		return new ResponseEntity<List<Enseignement>>(daoEnseignement.findAll(), HttpStatus.OK);
	}

	@JsonView(JsonViews.EnseignementAvecMatiere.class)
	@GetMapping("/matiere")
	public List<Enseignement> findAllEnseignementMatiere() {
		return daoEnseignement.findAll();
	}
	
	@JsonView(JsonViews.EnseignementAvecFormateur.class)
	@GetMapping("/formateur")
	public List<Enseignement> findAllEnseignementFormateur() {
		return daoEnseignement.findAll();
	}


	// --- By ID ---
	@GetMapping("/{id}")
	public ResponseEntity<Enseignement> findById(@PathVariable(name = "id") Integer id) {
		Optional<Enseignement> opt = daoEnseignement.findById(id);
		if (opt.isPresent()) {
			return new ResponseEntity<Enseignement>(opt.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// -- By ID avec ---

	@JsonView(JsonViews.EnseignementAvecMatiere.class)
	@GetMapping("/{id}/matiere")
	public ResponseEntity<Enseignement> findByIdWithMatiere(@PathVariable(name = "id") Integer id) {
		Optional<Enseignement> opt = daoEnseignement.findById(id);
		if (opt.isPresent()) {
			return new ResponseEntity<Enseignement>(opt.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@JsonView(JsonViews.EnseignementAvecFormateur.class)
	@GetMapping("/{id}/formateur")
	public ResponseEntity<Enseignement> findByIdWithFormateur(@PathVariable(name = "id") Integer id) {
		Optional<Enseignement> opt = daoEnseignement.findById(id);
		if (opt.isPresent()) {
			return new ResponseEntity<Enseignement>(opt.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// --- CREATE ---

	@JsonView(JsonViews.Enseignement.class)
	@PostMapping(value = { "", "/" })
	public ResponseEntity<Void> insert(@Valid @RequestBody Enseignement enseignement, BindingResult br,
			UriComponentsBuilder uCB) {
		if (br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		daoEnseignement.save(enseignement);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uCB.path("/rest/enseignement/{id}").buildAndExpand(enseignement.getId()).toUri());
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

	// --- UPDATE ---

	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable(name = "id") Integer id,
			@Valid @RequestBody Enseignement enseignement, BindingResult br) {
		if (br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Optional<Enseignement> opt = daoEnseignement.findById(id);
		if (opt.isPresent()) {
			enseignement.setVersion(opt.get().getVersion());
			enseignement.setId(enseignement.getId());;
			daoEnseignement.save(enseignement);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// --- DELETE ---
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable(name = "id") Integer id) {
		Optional<Enseignement> opt = daoEnseignement.findById(id);
		if (opt.isPresent()) {
			daoEnseignement.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
