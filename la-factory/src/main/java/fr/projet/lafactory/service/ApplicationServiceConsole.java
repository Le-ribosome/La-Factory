package fr.projet.lafactory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.projet.lafactory.dao.IDAOPersonne;
import fr.projet.lafactory.model.Droit;
import fr.projet.lafactory.model.Personne;
import fr.projet.lafactory.model.PersonneDroit;

@Service
public class ApplicationServiceConsole implements CommandLineRunner { // pour avoir accès à la console
	
	//pour le cryptage des mots de passes
	@Autowired
	private IDAOPersonne daoPersonne;
	@Autowired
	private PasswordEncoder pass;
	
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("La console est lancée, le programme est prêt à recevoir des requêtes");
		
		Droit d = Droit.DROIT_ADMIN;
		
		PersonneDroit pDroit = new PersonneDroit();
		pDroit.setDroit(d);
		
		//on crée les personnes avec leur mot de passe encrypté
		Personne p = new Personne();
		p.setEmail("marie@marie.fr");
		p.setMotDePasse(pass.encode("marie"));
		p.getDroits().add(pDroit);
		daoPersonne.save(p);
		
		Personne c = new Personne();
		c.setEmail("constance@constance.fr");
		c.setMotDePasse(pass.encode("constance"));
		c.getDroits().add(pDroit);
		daoPersonne.save(c);
		
		Personne karen = new Personne();
		karen.setEmail("karen@karen.fr");
		karen.setMotDePasse(pass.encode("karen"));
		karen.getDroits().add(pDroit);
		daoPersonne.save(karen);
		
		Personne alexis = new Personne();
		alexis.setEmail("alexis@alexis.fr");
		alexis.setMotDePasse(pass.encode("alexis"));
		alexis.getDroits().add(pDroit);
		daoPersonne.save(alexis);
		
		Personne helene = new Personne();
		helene.setEmail("helene@helene.fr");
		helene.setMotDePasse(pass.encode("helene"));
		helene.getDroits().add(pDroit);
		daoPersonne.save(helene);
		
		Personne sylvain = new Personne();
		sylvain.setEmail("sylvain@sylvain.fr");
		sylvain.setMotDePasse(pass.encode("sylvain"));
		sylvain.getDroits().add(pDroit);
		daoPersonne.save(sylvain);
		
		Personne gregoire = new Personne();
		gregoire.setEmail("gregoire@gregoire.fr");
		gregoire.setMotDePasse(pass.encode("gregoire"));
		gregoire.getDroits().add(pDroit);
		daoPersonne.save(gregoire);
		
		Personne emmanuelle = new Personne();
		emmanuelle.setEmail("emmanuelle@emmanuelle.fr");
		emmanuelle.setMotDePasse(pass.encode("emmanuelle"));
		emmanuelle.getDroits().add(pDroit);
		daoPersonne.save(emmanuelle);
		
		Personne benjamin = new Personne();
		benjamin.setEmail("benjamin@benjamin.fr");
		benjamin.setMotDePasse(pass.encode("benjamin"));
		benjamin.getDroits().add(pDroit);
		daoPersonne.save(benjamin);
		
//		Utilisateur u = daoUtilisateur.findById("karen").get();
//		u.setPassword(pass.encode("wallpaper"));
//		daoUtilisateur.save(u);
		
	}
	
	   
}