package fr.projet.lafactory.restcontroller;

import java.util.ArrayList;
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

import fr.projet.lafactory.dao.IDAOFormation;
import fr.projet.lafactory.model.Formation;
import fr.projet.lafactory.model.view.JsonViews;
import fr.projet.lafactory.model.view.JsonViews.FormationAvecSalle;

@RestController
@RequestMapping("/rest/formations")
@CrossOrigin(origins = "*")
public class FormationRestController {

	@Autowired
	private IDAOFormation daoFormation;

	// --- READ ---
	@JsonView(JsonViews.Formation.class)
	@GetMapping(value = { "", "/" })
	public ResponseEntity<List<Formation>> findAll() {
		return new ResponseEntity<List<Formation>>(daoFormation.findAll(), HttpStatus.OK);
	}

	@JsonView(JsonViews.FormationAvecStagiaire.class)
	@GetMapping(value = "/stagiaires")
	public ResponseEntity<List<Formation>> findAllWithStagiaires() {
		return new ResponseEntity<List<Formation>>(daoFormation.findAll(), HttpStatus.OK);
	}

	@JsonView(JsonViews.FormationAvecModule.class)
	@GetMapping(value = "/modules")
	public ResponseEntity<List<Formation>> findAllWithModules() {
		return new ResponseEntity<List<Formation>>(daoFormation.findAll(), HttpStatus.OK);
	}
	
	@JsonView(JsonViews.FormationAvecSalle.class)
	@GetMapping("/salle")
	public List<Formation> findAllFormationSalle() {
		return daoFormation.findAll();
	}

	@JsonView(JsonViews.FormationAvecGestionnaire.class)
	@GetMapping("/gestionnaire")
	public List<Formation> findAllFormationGestionnaire() {
		return daoFormation.findAll();
	}
	
	// --- By ID ---
	@JsonView(JsonViews.Formation.class)
	@GetMapping("/{id}")
	public ResponseEntity<Formation> findById(@PathVariable(name = "id") Integer id) {
		Optional<Formation> opt = daoFormation.findById(id);
		if (opt.isPresent()) {
			return new ResponseEntity<Formation>(opt.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// -- By ID avec Gestionnaire --

	@JsonView(JsonViews.FormationAvecGestionnaire.class)
	@GetMapping("/{id}/gestionnaire")
	public ResponseEntity<Formation> findByIdWithGestionnaire(@PathVariable(name = "id") Integer id) {
		Optional<Formation> opt = daoFormation.findById(id);
		if (opt.isPresent()) {
			return new ResponseEntity<Formation>(opt.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// -- By ID avec Salle --

	@JsonView(JsonViews.FormationAvecSalle.class)
	@GetMapping("/{id}/salle")
	public ResponseEntity<Formation> findByIdWithSalle(@PathVariable(name = "id") Integer id) {
		Optional<Formation> opt = daoFormation.findById(id);
		if (opt.isPresent()) {
			return new ResponseEntity<Formation>(opt.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// --- CREATE ---

	@JsonView(JsonViews.Formation.class)
	@PostMapping(value = { "", "/" })
	public ResponseEntity<Void> insert(@Valid @RequestBody Formation formation, BindingResult br,
			UriComponentsBuilder uCB) {
		if (br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		daoFormation.save(formation);
		HttpHeaders headers = new HttpHeaders();
		List<String> chaine = new ArrayList<String>();
		chaine.add("*");
		headers.setAccessControlAllowHeaders(chaine);
		headers.setAccessControlExposeHeaders(chaine);
		headers.setLocation(uCB.path("/rest/formation/{id}").buildAndExpand(formation.getId()).toUri());
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

	// --- UPDATE ---

	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable(name = "id") Integer id, @Valid @RequestBody Formation formation,
			BindingResult br) {
		if (br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Optional<Formation> opt = daoFormation.findById(id);
		if (opt.isPresent()) {
			formation.setVersion(opt.get().getVersion());
			formation.setId(id);
			daoFormation.save(formation);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// --- DELETE ---
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable(name = "id") Integer id) {
		Optional<Formation> opt = daoFormation.findById(id);
		if (opt.isPresent()) {
			daoFormation.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
