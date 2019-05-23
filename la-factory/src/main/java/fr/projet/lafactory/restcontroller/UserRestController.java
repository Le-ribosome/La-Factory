package fr.projet.lafactory.restcontroller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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

import fr.projet.lafactory.dao.IDAOAdministrateur;
import fr.projet.lafactory.dao.IDAOFormateur;
import fr.projet.lafactory.dao.IDAOGestionnaire;
import fr.projet.lafactory.dao.IDAOPersonne;
import fr.projet.lafactory.dao.IDAOStagiaire;
import fr.projet.lafactory.dao.IDAOTechnicien;
import fr.projet.lafactory.model.Administrateur;
import fr.projet.lafactory.model.Formateur;
import fr.projet.lafactory.model.Gestionnaire;
import fr.projet.lafactory.model.Personne;
import fr.projet.lafactory.model.Stagiaire;
import fr.projet.lafactory.model.Technicien;
import fr.projet.lafactory.model.view.JsonViews;

@RestController
@RequestMapping("/rest/user")
@CrossOrigin(origins = "*")
public class UserRestController {

	@Autowired
	private IDAOPersonne daoPersonne;
	
	@Autowired
	private IDAOFormateur daoFormateur;
	
	@Autowired
	private IDAOGestionnaire daoGestionnaire;
	
	@Autowired
	private IDAOStagiaire daoStagiaire;
	
	@Autowired
	private IDAOTechnicien daoTechnicien;
	
	@Autowired
	private IDAOAdministrateur daoAdministrateur;
	
	// les mots de passe seront encodés dès création d'une personne (formateur, stagiaire, etc.)
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// passwordEncoder.encode(user.getUsername();
	
//CREATE pour: 
	
	// Ajout formateur:
	@PostMapping(value = { "/formateur" })
	public ResponseEntity<Void> insert(@Valid @RequestBody Formateur formateur, BindingResult br,
			UriComponentsBuilder uCB) {
		if (br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		// sauvegarde en BDD du formateur mais mdp non encrypté
		daoFormateur.save(formateur);
		
		// on recup un formateur via la dao, on encrypte le mdp
		Formateur fBdd = daoFormateur.findById(formateur.getId()).get();
		
		// encrypte le mdp avant de sauvegarder dans la base
		fBdd.setMotDePasse(passwordEncoder.encode(formateur.getMotDePasse()));
		// on sauvegarde ds la base avec le mdp encrypté...normalement
		daoFormateur.save(fBdd);
		
		
// Test pour ajouter les droits au formateur		
//			daoFormateur.save(formateur);
//			PersonneDroit personneDroit = new PersonneDroit();
//			personneDroit.setPersonne(formateur);
//			personneDroit.setDroit(Droit.DROIT_FORMATEUR);
//			daoPersonnedroit.save(personneDroit);
		// Ça nous donne une uri, important car dans une reponse on a un body mais aussi
		// un header
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uCB.path("/rest/formateur/{id}").buildAndExpand(formateur.getId()).toUri());
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	// Ajout gestionnaire:
	@PostMapping(value = { "/gestionnaire" })
	public ResponseEntity<Void> insert(@Valid @RequestBody Gestionnaire gestionnaire, BindingResult br,
			UriComponentsBuilder uCB) {
		if (br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		daoGestionnaire.save(gestionnaire);
		// Ça nous donne une uri, important car dans une reponse on a un body mais aussi
		// un header
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uCB.path("/rest/gestionnaire/{id}").buildAndExpand(gestionnaire.getId()).toUri());
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	// Ajout stagiaire:
	@PostMapping(value = { "/stagiaire" })
	public ResponseEntity<Void> insert(@Valid @RequestBody Stagiaire stagiaire, BindingResult br,
			UriComponentsBuilder uCB) {
		if (br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		daoStagiaire.save(stagiaire);
		// Ça nous donne une uri, important car dans une reponse on a un body mais aussi
		// un header
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uCB.path("/rest/stagiaire/{id}").buildAndExpand(stagiaire.getId()).toUri());
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	// Ajout technicien:
	@PostMapping(value = { "/technicien" })
	public ResponseEntity<Void> insert(@Valid @RequestBody Technicien technicien, BindingResult br,
			UriComponentsBuilder uCB) {
		if (br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		daoTechnicien.save(technicien);
		// Ça nous donne une uri, important car dans une reponse on a un body mais aussi
		// un header
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uCB.path("/rest/technicien/{id}").buildAndExpand(technicien.getId()).toUri());
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	// Ajout administrateur:
		@PostMapping(value = { "/administrateur" })
		public ResponseEntity<Void> insert(@Valid @RequestBody Administrateur administrateur, BindingResult br,
				UriComponentsBuilder uCB) {
			if (br.hasErrors()) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			daoAdministrateur.save(administrateur);
			// Ça nous donne une uri, important car dans une reponse on a un body mais aussi
			// un header
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(uCB.path("/rest/administrateur/{id}").buildAndExpand(administrateur.getId()).toUri());
			return new ResponseEntity<>(HttpStatus.CREATED);
		}

//	READ pour:
	
	//Tout le monde: personne
	@JsonView(JsonViews.User.class)
	@GetMapping(value = {"","/all"})
	public ResponseEntity<List<Personne>> findAll() {
		return new ResponseEntity<List<Personne>>(daoPersonne.findAll(), HttpStatus.OK);
	}

	//Une personne avec un email précis: personne
	@JsonView(JsonViews.User.class)
	@GetMapping(value = {"/{email}"})
	public ResponseEntity<Personne> findByEmail(@PathVariable(name="email") String email) {
		return new ResponseEntity<Personne>(daoPersonne.findByEmail(email).get(), HttpStatus.OK);
	}
	
	//Formateurs
	@JsonView(JsonViews.Formateur.class)
	@GetMapping(value = {"/formateurs"})
	public ResponseEntity<List<Formateur>> findAllFormateurs() {
		return new ResponseEntity<List<Formateur>>(daoFormateur.findAll(), HttpStatus.OK);
	}
	
	@JsonView(JsonViews.Formateur.class)
	@GetMapping("/formateur/{id}")
	public ResponseEntity<Formateur> findFormateurById(@PathVariable(name="id") Integer id){
		Optional<Formateur> opt = daoFormateur.findById(id);
		if (opt.isPresent()) {
			return new ResponseEntity<Formateur>(opt.get(), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	//Gestionnaires
	@JsonView(JsonViews.Gestionnaire.class)
	@GetMapping(value = {"/gestionnaires"})
	public ResponseEntity<List<Gestionnaire>> findAllGestionnaires() {
		return new ResponseEntity<List<Gestionnaire>>(daoGestionnaire.findAll(), HttpStatus.OK);
	}
	
	@JsonView(JsonViews.Gestionnaire.class)
	@GetMapping("/gestionnaire/{id}")
	public ResponseEntity<Gestionnaire> findGestionnaireById(@PathVariable(name="id") Integer id){
		Optional<Gestionnaire> opt = daoGestionnaire.findById(id);
		if (opt.isPresent()) {
			return new ResponseEntity<Gestionnaire>(opt.get(), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	//Stagiaires
	@JsonView(JsonViews.Stagiaire.class)
	@GetMapping(value = {"/stagiaires"})
	public ResponseEntity<List<Stagiaire>> findAllStagiaires() {
		return new ResponseEntity<List<Stagiaire>>(daoStagiaire.findAll(), HttpStatus.OK);
	}
	
	@JsonView(JsonViews.Stagiaire.class)
	@GetMapping("/stagiaire/{id}")
	public ResponseEntity<Stagiaire> findStagiaireById(@PathVariable(name="id") Integer id){
		Optional<Stagiaire> opt = daoStagiaire.findById(id);
		if (opt.isPresent()) {
			return new ResponseEntity<Stagiaire>(opt.get(), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	//Techniciens
	@JsonView(JsonViews.Technicien.class)
	@GetMapping(value = {"/techniciens"})
	public ResponseEntity<List<Technicien>> findAllTechniciens() {
		return new ResponseEntity<List<Technicien>>(daoTechnicien.findAll(), HttpStatus.OK);
	}
	
	@JsonView(JsonViews.Technicien.class)
	@GetMapping("/technicien/{id}")
	public ResponseEntity<Technicien> findTechniciensById(@PathVariable(name="id") Integer id){
		Optional<Technicien> opt = daoTechnicien.findById(id);
		if (opt.isPresent()) {
			return new ResponseEntity<Technicien>(opt.get(), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	//Administrateur
		@JsonView(JsonViews.Administrateur.class)
		@GetMapping(value = {"/administrateurs"})
		public ResponseEntity<List<Administrateur>> findAllAdministrateurs() {
			return new ResponseEntity<List<Administrateur>>(daoAdministrateur.findAll(), HttpStatus.OK);
		}
		
		@JsonView(JsonViews.Administrateur.class)
		@GetMapping("/administrateur/{id}")
		public ResponseEntity<Administrateur> findAdministrateurById(@PathVariable(name="id") Integer id){
			Optional<Administrateur> opt = daoAdministrateur.findById(id);
			if (opt.isPresent()) {
				return new ResponseEntity<Administrateur>(opt.get(), HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
	
//UPDATE pour: 
	
	@PutMapping("/formateur/{id}")
    public ResponseEntity<Void> update(@PathVariable(name="id") Integer id, @Valid @RequestBody Formateur formateur, BindingResult br) {
        if (br.hasErrors()){
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        } else {
            Optional<Formateur> opt = daoFormateur.findById(id);
            if(opt.isPresent()) {
                formateur.setVersion(opt.get().getVersion());
                formateur.setId(id);
                daoFormateur.save(formateur);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }    
    }
	
	@PutMapping("/gestionnaire/{id}")
    public ResponseEntity<Void> update(@PathVariable(name="id") Integer id, @Valid @RequestBody Gestionnaire gestionnaire, BindingResult br) {
        if (br.hasErrors()){
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        } else {
            Optional<Gestionnaire> opt = daoGestionnaire.findById(id);
            if(opt.isPresent()) {
                gestionnaire.setVersion(opt.get().getVersion());
                gestionnaire.setId(id);
                daoGestionnaire.save(gestionnaire);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }    
    }

	@PutMapping("/stagiaire/{id}")
    public ResponseEntity<Void> update(@PathVariable(name="id") Integer id, @Valid @RequestBody Stagiaire stagiaire, BindingResult br) {
        if (br.hasErrors()){
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        } else {
            Optional<Stagiaire> opt = daoStagiaire.findById(id);
            if(opt.isPresent()) {
                stagiaire.setVersion(opt.get().getVersion());
                stagiaire.setId(id);
                daoStagiaire.save(stagiaire);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }    
    }
	
	@PutMapping("/technicien/{id}")
    public ResponseEntity<Void> update(@PathVariable(name="id") Integer id, @Valid @RequestBody Technicien technicien, BindingResult br) {
        if (br.hasErrors()){
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        } else {
            Optional<Technicien> opt = daoTechnicien.findById(id);
            if(opt.isPresent()) {
                technicien.setVersion(opt.get().getVersion());
                technicien.setId(id);
                daoTechnicien.save(technicien);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }    
    }
	
	@PutMapping("/administrateur/{id}")
    public ResponseEntity<Void> update(@PathVariable(name="id") Integer id, @Valid @RequestBody Administrateur administrateur, BindingResult br) {
        if (br.hasErrors()){
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        } else {
            Optional<Administrateur> opt = daoAdministrateur.findById(id);
            if(opt.isPresent()) {
            	administrateur.setVersion(opt.get().getVersion());
            	administrateur.setId(id);
                daoAdministrateur.save(administrateur);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }    
    }
	
	
	@DeleteMapping("/formateur/{id}")
	public ResponseEntity<Void> deleteFormateur(@PathVariable(name="id") Integer id){
		Optional<Formateur> opt = daoFormateur.findById(id);
		if (opt.isPresent()) {
			daoFormateur.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/gestionnaire/{id}")
	public ResponseEntity<Void> deleteGestionnaire(@PathVariable(name="id") Integer id){
		Optional<Gestionnaire> opt = daoGestionnaire.findById(id);
		if (opt.isPresent()) {
			daoGestionnaire.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@DeleteMapping("/stagiaire/{id}")
	public ResponseEntity<Void> deleteStagiaire(@PathVariable(name="id") Integer id){
		Optional<Stagiaire> opt = daoStagiaire.findById(id);
		if (opt.isPresent()) {
			daoStagiaire.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/technicien/{id}")
	public ResponseEntity<Void> deleteTechnicien(@PathVariable(name="id") Integer id){
		Optional<Technicien> opt = daoTechnicien.findById(id);
		if (opt.isPresent()) {
			daoTechnicien.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	
	@DeleteMapping("/administrateur/{id}")
	public ResponseEntity<Void> deleteAdministrateur(@PathVariable(name="id") Integer id){
		Optional<Administrateur> opt = daoAdministrateur.findById(id);
		if (opt.isPresent()) {
			daoAdministrateur.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	
}
