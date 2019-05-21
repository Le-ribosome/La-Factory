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

import fr.projet.lafactory.dao.IDAOTechnicien;
import fr.projet.lafactory.model.Technicien;
import fr.projet.lafactory.model.view.JsonViews;

public class TechnicienRestController {

	@Autowired
	private IDAOTechnicien daoTechnicien;

	// --- READ ---
	@JsonView(JsonViews.User.class)
	@GetMapping(value = { "", "/" })
	public ResponseEntity<List<Technicien>> findAll() {
		return new ResponseEntity<List<Technicien>>(daoTechnicien.findAll(), HttpStatus.OK);
	}

	// --- By ID ---
	@GetMapping("/{id}")
	public ResponseEntity<Technicien> findById(@PathVariable(name = "id") Integer id) {
		Optional<Technicien> opt = daoTechnicien.findById(id);
		if (opt.isPresent()) {
			return new ResponseEntity<Technicien>(opt.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// --- CREATE ---

	@JsonView(JsonViews.Technicien.class)
	@PostMapping(value = { "", "/" })
	public ResponseEntity<Void> insert(@Valid @RequestBody Technicien technicien, BindingResult br,
			UriComponentsBuilder uCB) {
		if (br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		daoTechnicien.save(technicien);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uCB.path("/rest/technicien/{id}").buildAndExpand(technicien.getId()).toUri());
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

	// --- UPDATE ---

	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable(name = "id") Integer id, @Valid @RequestBody Technicien technicien,
			BindingResult br) {
		if (br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Optional<Technicien> opt = daoTechnicien.findById(id);
		if (opt.isPresent()) {
			technicien.setVersion(opt.get().getVersion());
			technicien.setId(id);
			daoTechnicien.save(technicien);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// --- DELETE ---
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable(name = "id") Integer id) {
		Optional<Technicien> opt = daoTechnicien.findById(id);
		if (opt.isPresent()) {
			daoTechnicien.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
