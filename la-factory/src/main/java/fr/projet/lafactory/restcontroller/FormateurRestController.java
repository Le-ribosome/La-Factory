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

import fr.projet.lafactory.dao.IDAOFormateur;
import fr.projet.lafactory.model.Formateur;
import fr.projet.lafactory.model.view.JsonViews;

@RestController
@RequestMapping("/rest/formateur")
@CrossOrigin(origins = "*")
public class FormateurRestController {

	@Autowired
	private IDAOFormateur daoFormateur;

	// --- READ ---
	@JsonView(JsonViews.User.class)
	@GetMapping(value = { "", "/" })
	public ResponseEntity<List<Formateur>> findAll() {
		return new ResponseEntity<List<Formateur>>(daoFormateur.findAll(), HttpStatus.OK);
	}
	

	// --- By ID ---
	@GetMapping("/{id}")
	public ResponseEntity<Formateur> findById(@PathVariable(name = "id") Integer id) {
		Optional<Formateur> opt = daoFormateur.findById(id);
		if (opt.isPresent()) {
			return new ResponseEntity<Formateur>(opt.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}


	// --- CREATE ---

	@JsonView(JsonViews.Formateur.class)
	@PostMapping(value = { "", "/" })
	public ResponseEntity<Void> insert(@Valid @RequestBody Formateur formateur, BindingResult br,
			UriComponentsBuilder uCB) {
		if (br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		daoFormateur.save(formateur);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uCB.path("/rest/module/{id}").buildAndExpand(formateur.getId()).toUri());
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

	// --- UPDATE ---

	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable(name = "id") Integer id, @Valid @RequestBody Formateur formateur,
			BindingResult br) {
		if (br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Optional<Formateur> opt = daoFormateur.findById(id);
		if (opt.isPresent()) {
			formateur.setVersion(opt.get().getVersion());
			formateur.setId(id);
			daoFormateur.save(formateur);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// --- DELETE ---
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable(name = "id") Integer id) {
		Optional<Formateur> opt = daoFormateur.findById(id);
		if (opt.isPresent()) {
			daoFormateur.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
