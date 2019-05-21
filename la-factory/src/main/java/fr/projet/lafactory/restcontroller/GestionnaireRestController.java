package fr.projet.lafactory.restcontroller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonView;

import fr.projet.lafactory.dao.IDAOGestionnaire;
import fr.projet.lafactory.model.Gestionnaire;
import fr.projet.lafactory.model.view.JsonViews;

public class GestionnaireRestController {
	@Autowired
	private IDAOGestionnaire daoGestionnaire;

	// --- READ ---
	@JsonView(JsonViews.User.class)
	@GetMapping(value = { "", "/" })
	public ResponseEntity<List<Gestionnaire>> findAll() {
		return new ResponseEntity<List<Gestionnaire>>(daoGestionnaire.findAll(), HttpStatus.OK);
	}

			
	// --- By ID ---
	@GetMapping("/{id}")
	public ResponseEntity<Gestionnaire> findById(@PathVariable(name = "id") Integer id) {
		Optional<Gestionnaire> opt = daoGestionnaire.findById(id);
		if (opt.isPresent()) {
			return new ResponseEntity<Gestionnaire>(opt.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}


	// --- CREATE ---

	@JsonView(JsonViews.Gestionnaire.class)
	@PostMapping(value = { "", "/" })
	public ResponseEntity<Void> insert(@Valid @RequestBody Gestionnaire gestionnaire, BindingResult br,
			UriComponentsBuilder uCB) {
		if (br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		daoGestionnaire.save(gestionnaire);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uCB.path("/rest/salle/{id}").buildAndExpand(gestionnaire.getId()).toUri());
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

	// --- UPDATE ---

	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable(name = "id") Integer id, @Valid @RequestBody Gestionnaire gestionnaire,
			BindingResult br) {
		if (br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Optional<Gestionnaire> opt = daoGestionnaire.findById(id);
		if (opt.isPresent()) {
			gestionnaire.setVersion(opt.get().getVersion());
			gestionnaire.setId(id);
			daoGestionnaire.save(gestionnaire);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// --- DELETE ---
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable(name = "id") Integer id) {
		Optional<Gestionnaire> opt = daoGestionnaire.findById(id);
		if (opt.isPresent()) {
			daoGestionnaire.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
