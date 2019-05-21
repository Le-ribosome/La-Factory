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

import fr.projet.lafactory.dao.IDAOIndisponibilite;
import fr.projet.lafactory.model.Indisponibilite;
import fr.projet.lafactory.model.view.JsonViews;
import fr.projet.lafactory.model.view.JsonViews.ModuleAvecFormateur;

public class IndisponibiliteRestController {
	
	@Autowired
	private IDAOIndisponibilite daoIndisponibilite;

	// --- READ ---
	@JsonView(JsonViews.User.class)
	@GetMapping(value = { "", "/" })
	public ResponseEntity<List<Indisponibilite>> findAll() {
		return new ResponseEntity<List<Indisponibilite>>(daoIndisponibilite.findAll(), HttpStatus.OK);
	}

	@JsonView(ModuleAvecFormateur.class)
	@GetMapping("/formateur")
	public List<Indisponibilite> findAllIndisponibiliteSalle() {
		return daoIndisponibilite.findAll();
	}
	
	
	// --- By ID ---
	@GetMapping("/{id}")
	public ResponseEntity<Indisponibilite> findById(@PathVariable(name = "id") Integer id) {
		Optional<Indisponibilite> opt = daoIndisponibilite.findById(id);
		if (opt.isPresent()) {
			return new ResponseEntity<Indisponibilite>(opt.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// -- By ID avec Formateur --

	@JsonView(JsonViews.IndisponibiliteAvecFormateur.class)
		@GetMapping("/{id}/formateur")
		public ResponseEntity<Indisponibilite> findByIdWithFormateur(@PathVariable(name="id") Integer id) {
		Optional<Indisponibilite> opt = daoIndisponibilite.findById(id);
		if (opt.isPresent()) {
			return new ResponseEntity<Indisponibilite>(opt.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}


	// --- CREATE ---

	@JsonView(JsonViews.Indisponibilite.class)
	@PostMapping(value = { "", "/" })
	public ResponseEntity<Void> insert(@Valid @RequestBody Indisponibilite indisponibilite, BindingResult br,
			UriComponentsBuilder uCB) {
		if (br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		daoIndisponibilite.save(indisponibilite);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uCB.path("/rest/indisponibilite/{id}").buildAndExpand(indisponibilite.getId()).toUri());
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}
	

	// --- UPDATE ---

	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable(name = "id") Integer id, @Valid @RequestBody Indisponibilite indisponibilite,
			BindingResult br) {
		if (br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Optional<Indisponibilite> opt = daoIndisponibilite.findById(id);
		if (opt.isPresent()) {
			indisponibilite.setVersion(opt.get().getVersion());
			indisponibilite.setId(id);
			daoIndisponibilite.save(indisponibilite);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// --- DELETE ---
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable(name = "id") Integer id) {
		Optional<Indisponibilite> opt = daoIndisponibilite.findById(id);
		if (opt.isPresent()) {
			daoIndisponibilite.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
