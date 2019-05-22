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

import fr.projet.lafactory.dao.IDAOSalle;
import fr.projet.lafactory.model.Salle;
import fr.projet.lafactory.model.view.JsonViews;

@RestController
@RequestMapping("/rest/salle")
@CrossOrigin(origins = "*")
public class SalleRestController {

	@Autowired
	private IDAOSalle daoSalle;

	// --- READ ---
	@GetMapping(value = { "", "/" })
	@JsonView(JsonViews.SalleAvecVideoprojecteur.class)
	public ResponseEntity<List<Salle>> findAll() {
		return new ResponseEntity<List<Salle>>(daoSalle.findAll(), HttpStatus.OK);
	}

	// -- By ID ---
	@GetMapping("/{id}")
	public ResponseEntity<Salle> findById(@PathVariable(name = "id") Integer id) {
		Optional<Salle> opt = daoSalle.findById(id);
		if (opt.isPresent()) {
			return new ResponseEntity<Salle>(opt.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// -- By ID avec Videoproj --
	@JsonView(JsonViews.SalleAvecVideoprojecteur.class)
	@GetMapping("/{id}/videoprojecteur")
	public ResponseEntity<Salle> findByIdAvecVideoprojecteur(@PathVariable(name = "id") Integer id) {
		Optional<Salle> opt = daoSalle.findById(id);
		if (opt.isPresent()) {
			return new ResponseEntity<Salle>(opt.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// -- By ID avec Formations --
	@JsonView(JsonViews.SalleAvecFormations.class)
	@GetMapping("/{id}/formations")
	public ResponseEntity<Salle> findByIdAvecFormations(@PathVariable(name = "id") Integer id) {
		Optional<Salle> opt = daoSalle.findById(id);
		if (opt.isPresent()) {
			return new ResponseEntity<Salle>(opt.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	// --- CREATE ---

	@JsonView(JsonViews.Salle.class)
	@PostMapping(value = { "", "/" })
	public ResponseEntity<Void> insert(@Valid @RequestBody Salle salle, BindingResult br, UriComponentsBuilder uCB) {
		if (br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		daoSalle.save(salle);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uCB.path("/rest/salle/{id}").buildAndExpand(salle.getId()).toUri());
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

	// --- UPDATE ---

	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable(name = "id") Integer id, @Valid @RequestBody Salle salle,
			BindingResult br) {
		if (br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Optional<Salle> opt = daoSalle.findById(id);
		if (opt.isPresent()) {
			salle.setVersion(opt.get().getVersion());
			salle.setId(id);
			daoSalle.save(salle);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// --- DELETE ---
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable(name = "id") Integer id) {
		Optional<Salle> opt = daoSalle.findById(id);
		if (opt.isPresent()) {
			daoSalle.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
