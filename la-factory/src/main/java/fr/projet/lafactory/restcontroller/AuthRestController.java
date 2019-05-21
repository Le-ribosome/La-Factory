package fr.projet.restcontroller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/rest/login")
public class AuthRestController {
	@GetMapping("")
	public ResponseEntity<Void> login(){
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
