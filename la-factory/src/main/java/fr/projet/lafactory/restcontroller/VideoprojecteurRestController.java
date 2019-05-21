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

import fr.projet.lafactory.dao.IDAOVideoprojecteur;
import fr.projet.lafactory.model.Videoprojecteur;
import fr.projet.lafactory.model.view.JsonViews;

@RestController
@RequestMapping("/rest/videoprojecteur")
@CrossOrigin(origins = "*")
public class VideoprojecteurRestController {

	@Autowired
	private IDAOVideoprojecteur daoVideoprojecteur;

	// --- READ ---

	@GetMapping(value = { "", "/" })
	@JsonView(JsonViews.Videoprojecteur.class)
	public ResponseEntity<List<Videoprojecteur>> findAll() {
		return new ResponseEntity<List<Videoprojecteur>>(daoVideoprojecteur.findAll(), HttpStatus.OK);
	}

	// -- By ID ---
	@JsonView(JsonViews.Videoprojecteur.class)
	@GetMapping("/{id}")
	public ResponseEntity<Videoprojecteur> findById(@PathVariable(name = "id") Integer id) {
		Optional<Videoprojecteur> opt = daoVideoprojecteur.findById(id);
		if (opt.isPresent()) {
			return new ResponseEntity<Videoprojecteur>(opt.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// -- By ID avec Salle --
	@JsonView(JsonViews.VideoprojecteurAvecSalle.class)
	@GetMapping("/{id}/salle")
	public ResponseEntity<Videoprojecteur> findByIdAvecSalle(@PathVariable(name = "id") Integer id) {
		Optional<Videoprojecteur> opt = daoVideoprojecteur.findById(id);
		if (opt.isPresent()) {
			return new ResponseEntity<Videoprojecteur>(opt.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// --- CREATE ---

	// @JsonView(JsonViews.Videoprojecteur.class)
	@PostMapping(value = { "", "/" })
	public ResponseEntity<Void> insert(@Valid @RequestBody Videoprojecteur videoprojecteur, BindingResult br,
			UriComponentsBuilder uCB) {
		if (br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		daoVideoprojecteur.save(videoprojecteur);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uCB.path("/rest/videoprojecteur/{id}").buildAndExpand(videoprojecteur.getId()).toUri());
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

	// --- UPDATE ---
	//
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable(name = "id") Integer id,
			@Valid @RequestBody Videoprojecteur videoprojecteur, BindingResult br) {
		if (br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Optional<Videoprojecteur> opt = daoVideoprojecteur.findById(id);
		if (opt.isPresent()) {
			videoprojecteur.setVersion(opt.get().getVersion());
			videoprojecteur.setId(id);
			daoVideoprojecteur.save(videoprojecteur);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// --- DELETE ---
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable(name = "id") Integer id) {
		Optional<Videoprojecteur> opt = daoVideoprojecteur.findById(id);
		if (opt.isPresent()) {
			daoVideoprojecteur.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

}
