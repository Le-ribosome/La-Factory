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

import fr.projet.lafactory.dao.IDAOStagiaire;
import fr.projet.lafactory.model.Stagiaire;
import fr.projet.lafactory.model.view.JsonViews;

@RestController
@RequestMapping("/rest/stagiaire")
@CrossOrigin(origins = "*")
public class StagiaireRestController {

	@Autowired
	private IDAOStagiaire daoStagiaire;

	// --- READ ---
	@JsonView(JsonViews.User.class)
	@GetMapping(value = { "", "/" })
	public ResponseEntity<List<Stagiaire>> findAll() {
		return new ResponseEntity<List<Stagiaire>>(daoStagiaire.findAll(), HttpStatus.OK);
	}

	// --- By ID ---
	@GetMapping("/{id}")
	public ResponseEntity<Stagiaire> findById(@PathVariable(name = "id") Integer id) {
		Optional<Stagiaire> opt = daoStagiaire.findById(id);
		if (opt.isPresent()) {
			return new ResponseEntity<Stagiaire>(opt.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// -- By ID avec Formation --
	@JsonView(JsonViews.StagiaireAvecFormation.class)
	@GetMapping("/{id}/formation")
	public ResponseEntity<Stagiaire> findByIdAvecFormation(@PathVariable(name = "id") Integer id) {
		Optional<Stagiaire> opt = daoStagiaire.findById(id);
		if (opt.isPresent()) {
			return new ResponseEntity<Stagiaire>(opt.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// -- By ID avec Ordinateur --
	@JsonView(JsonViews.StagiaireAvecOrdinateur.class)
	@GetMapping("/{id}/ordinateur")
	public ResponseEntity<Stagiaire> findByIdAvecOrdinateur(@PathVariable(name = "id") Integer id) {
		Optional<Stagiaire> opt = daoStagiaire.findById(id);
		if (opt.isPresent()) {
			return new ResponseEntity<Stagiaire>(opt.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// --- CREATE ---

	@JsonView(JsonViews.Stagiaire.class)
	@PostMapping(value = { "", "/" })
	public ResponseEntity<Void> insert(@Valid @RequestBody Stagiaire stagiaire, BindingResult br,
			UriComponentsBuilder uCB) {
		if (br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		daoStagiaire.save(stagiaire);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uCB.path("/rest/salle/{id}").buildAndExpand(stagiaire.getId()).toUri());
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

	// --- UPDATE ---

	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable(name = "id") Integer id, @Valid @RequestBody Stagiaire stagiaire,
			BindingResult br) {
		if (br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Optional<Stagiaire> opt = daoStagiaire.findById(id);
		if (opt.isPresent()) {
			stagiaire.setVersion(opt.get().getVersion());
			stagiaire.setId(id);
			daoStagiaire.save(stagiaire);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// --- DELETE ---
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable(name = "id") Integer id) {
		Optional<Stagiaire> opt = daoStagiaire.findById(id);
		if (opt.isPresent()) {
			daoStagiaire.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
