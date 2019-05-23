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

import fr.projet.lafactory.dao.IDAOFormation;
import fr.projet.lafactory.dao.IDAOModule;
import fr.projet.lafactory.model.Formation;
import fr.projet.lafactory.model.Module;
import fr.projet.lafactory.model.view.JsonViews;

@RestController
@RequestMapping("/rest/module")
@CrossOrigin(origins = "*")
public class ModuleRestController {

	@Autowired
	private IDAOModule daoModule;
	
	@Autowired
	private IDAOFormation daoFormation;

	// --- READ ---
	@JsonView(JsonViews.Module.class)
	@GetMapping(value = { "", "/" })
	public ResponseEntity<List<Module>> findAll() {
		return new ResponseEntity<List<Module>>(daoModule.findAll(), HttpStatus.OK);
	}

	@JsonView(JsonViews.ModuleAvecMatiere.class)
	@GetMapping("/matiere")
	public List<Module> findAllModuleMatiere() {
		return daoModule.findAll();
	}
//	
//	@JsonView(JsonViews.ModuleAvecFormateur.class)
//	@GetMapping("/formateur")
//	public List<Module> findAllModuleFormateur() {
//		return daoModule.findAll();
//	}
//	
//	@JsonView(JsonViews.ModuleAvecFormation.class)
//	@GetMapping("/formation")
//	public List<Module> findAllModuleFormation() {
//		return daoModule.findAll();
//	}

//	Chercher un module par formation, pour le formation edit angular: 
	
//	@GetMapping("/byFormation/{id}")
//	public ResponseEntity<List<Module>> findByFormation(@PathVariable(name = "id") Integer id) {
//		Optional<Formation> formation = daoFormation.findById(id);
//		
//		if (formation.isPresent()) {
//			List<Module> opt = daoModule.findAllByFormation(formation.get());
//			if (opt.size()!=0) {
//				return new ResponseEntity<List<Module>>(opt, HttpStatus.OK);
//			} else {
//				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//			}
//		}
//		else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//			
//	}
	
//	@GetMapping("/byFormation/{id}")
//	public ResponseEntity<List<Module>> findByFormation(@PathVariable(name = "id") Integer id) {
//		List<Module> listeModules = daoModule.findAllByFormationId(id);
//			if (listeModules.size()>=0) {
//				return new ResponseEntity<List<Module>>(listeModules, HttpStatus.OK);
//			} else {
//				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//			}
//		}
	
	
	// --- By ID ---
	@JsonView(JsonViews.Module.class)
	@GetMapping("/{id}")
	public ResponseEntity<Module> findById(@PathVariable(name = "id") Integer id) {
		Optional<Module> opt = daoModule.findById(id);
		if (opt.isPresent()) {
			return new ResponseEntity<Module>(opt.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

//	// -- By ID avec Formateur --
//
//	@JsonView(JsonViews.ModuleAvecFormateur.class)
//		@GetMapping("/{id}/formateur")
//		public ResponseEntity<Module> findByIdWithFormateur(@PathVariable(name="id") Integer id) {
//		Optional<Module> opt = daoModule.findById(id);
//		if (opt.isPresent()) {
//			return new ResponseEntity<Module>(opt.get(), HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//	}

	// -- By ID avec Formation --

//	@JsonView(JsonViews.ModuleAvecFormation.class)
//		@GetMapping("/{id}/formation")
//		public ResponseEntity<Module> findByIdWithFormation(@PathVariable(name="id") Integer id) {
//		Optional<Module> opt = daoModule.findById(id);
//		if (opt.isPresent()) {
//			return new ResponseEntity<Module>(opt.get(), HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//	}
//
//	// -- By ID avec Matiere --
//
	@JsonView(JsonViews.ModuleAvecMatiere.class)
	@GetMapping("/{id}/matiere")
	public ResponseEntity<Module> findByIdWithMatiere(@PathVariable(name="id") Integer id) {
	Optional<Module> opt = daoModule.findById(id);
	if (opt.isPresent()) {
		return new ResponseEntity<Module>(opt.get(), HttpStatus.OK);
	} else {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	}

	// --- CREATE ---

	@JsonView(JsonViews.Module.class)
	@PostMapping(value = { "", "/" })
	public ResponseEntity<Void> insert(@Valid @RequestBody Module module, BindingResult br,
			UriComponentsBuilder uCB) {
		if (br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		daoModule.save(module);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uCB.path("/rest/module/{id}").buildAndExpand(module.getId()).toUri());
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

	// --- UPDATE ---

	@JsonView(JsonViews.Module.class)
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable(name = "id") Integer id, @Valid @RequestBody Module module,
			BindingResult br) {
		if (br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Optional<Module> opt = daoModule.findById(id);
		if (opt.isPresent()) {
			module.setVersion(opt.get().getVersion());
			module.setId(id);
			daoModule.save(module);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// --- DELETE ---
	@JsonView(JsonViews.Module.class)
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable(name = "id") Integer id) {
		Optional<Module> opt = daoModule.findById(id);
		if (opt.isPresent()) {
			daoModule.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
