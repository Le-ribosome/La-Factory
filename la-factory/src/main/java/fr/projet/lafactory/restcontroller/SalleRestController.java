package fr.projet.lafactory.restcontroller; 
 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.bind.annotation.CrossOrigin; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RestController; 
 
import fr.projet.lafactory.dao.IDAOSalle; 
 
 
@RestController 
@RequestMapping("/rest/salle") 
@CrossOrigin(origins = "*") 
public class SalleRestController { 
	 
	@Autowired 
	private IDAOSalle daoSalle; 
	 
	 
 
} 
