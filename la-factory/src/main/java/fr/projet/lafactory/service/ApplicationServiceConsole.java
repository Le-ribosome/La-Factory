package fr.projet.lafactory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.projet.lafactory.dao.IDAOFormateur;
import fr.projet.lafactory.dao.IDAOOrdinateur;
import fr.projet.lafactory.dao.IDAOPersonne;
import fr.projet.lafactory.dao.IDAOPersonneDroit;
import fr.projet.lafactory.dao.IDAOSalle;
import fr.projet.lafactory.dao.IDAOVideoprojecteur;
import fr.projet.lafactory.model.Droit;
import fr.projet.lafactory.model.Formateur;
import fr.projet.lafactory.model.Ordinateur;
import fr.projet.lafactory.model.Personne;
import fr.projet.lafactory.model.PersonneDroit;
import fr.projet.lafactory.model.Salle;
import fr.projet.lafactory.model.Videoprojecteur;

@Service
public class ApplicationServiceConsole implements CommandLineRunner { // pour avoir accès à la console
	
	//pour le cryptage des mots de passes
//	@Autowired
//	private IDAOPersonne daoPersonne;
//	@Autowired
//	private IDAOPersonneDroit daoPersonneDroit;
//	@Autowired
//	private PasswordEncoder pass;
//	@Autowired
	
//	private IDAOFormateur daoFormateur;
//	@Autowired
//	private IDAOVideoprojecteur daoVideoprojecteur;
//	@Autowired
//	private IDAOSalle daoSalle;
//	@Autowired
//	private IDAOOrdinateur daoOrdinateur;
	
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("La console est lancée, le programme est prêt à recevoir des requêtes");
		
		Droit d = Droit.DROIT_ADMIN;
		
		//On crée du matériel
//		Salle s = new Salle();
//		s.setCapacite(12);
//		s.setCode("SA01");
//		s.setCoutJournee(30);
//		daoSalle.save(s);
//		
//		Videoprojecteur vd = new Videoprojecteur();
//		vd.setCode("VD01");
//		vd.setCoutJournee(2);
//		daoVideoprojecteur.save(vd);
//		
//		Ordinateur ordi = new Ordinateur();
//		ordi.setAnneeAchat(2019);
//		ordi.setCode("OR01");
//		ordi.setCoutJournee(50);
//		ordi.setProcesseur("processeur au hasard");
//		ordi.setQuantiteRAM(4);
//		ordi.setQuantiteDD(500);
//		daoOrdinateur.save(ordi);
				
		//on crée les personnes avec leur mot de passe encrypté
//		Personne p = new Personne();
//		p.setEmail("marie@marie.fr");
//		p.setMotDePasse(pass.encode("marie"));
//		
//		pDroit.setDroit(d);
//		pDroit.setPersonne(p);
//		daoPersonne.save(p);
//		daoPersonneDroit.save(pDroit);
		
		
//		Personne c = new Personne();
//		c.setEmail("constance@constance.fr");
//		c.setMotDePasse(pass.encode("constance"));
//		PersonneDroit cDroit = new PersonneDroit();
//		cDroit.setDroit(d);
//		cDroit.setPersonne(c);
//		daoPersonne.save(c);
//		daoPersonneDroit.save(cDroit);
//		
//		
//		Personne karen = new Personne();
//		karen.setEmail("karen@karen.fr");
//		karen.setMotDePasse(pass.encode("karen"));
//		PersonneDroit karenDroit = new PersonneDroit();
//		karenDroit.setDroit(d);
//		karenDroit.setPersonne(karen);
//		daoPersonne.save(karen);
//		daoPersonneDroit.save(karenDroit);
//		
//		
//		Personne alexis = new Personne();
//		alexis.setEmail("alexis@alexis.fr");
//		alexis.setMotDePasse(pass.encode("alexis"));
//		PersonneDroit alexisDroit = new PersonneDroit();
//		alexisDroit.setDroit(d);
//		alexisDroit.setPersonne(alexis);
//		daoPersonne.save(alexis);
//		daoPersonneDroit.save(alexisDroit);
//		
//		
//		Personne helene = new Personne();
//		helene.setEmail("helene@helene.fr");
//		helene.setMotDePasse(pass.encode("helene"));
//		PersonneDroit heleneDroit = new PersonneDroit();
//		heleneDroit.setDroit(d);
//		heleneDroit.setPersonne(helene);
//		daoPersonne.save(helene);
//		daoPersonneDroit.save(heleneDroit);
//		
//		
//		Personne sylvain = new Personne();
//		sylvain.setEmail("sylvain@sylvain.fr");
//		sylvain.setMotDePasse(pass.encode("sylvain"));
//		PersonneDroit sylvainDroit = new PersonneDroit();
//		sylvainDroit.setDroit(d);
//		sylvainDroit.setPersonne(sylvain);
//		daoPersonne.save(sylvain);
//		daoPersonneDroit.save(sylvainDroit);
//		
//		
//		Personne gregoire = new Personne();
//		gregoire.setEmail("gregoire@gregoire.fr");
//		gregoire.setMotDePasse(pass.encode("gregoire"));
//		PersonneDroit gregoireDroit = new PersonneDroit();
//		gregoireDroit.setDroit(d);
//		gregoireDroit.setPersonne(gregoire);
//		daoPersonne.save(gregoire);
//		daoPersonneDroit.save(gregoireDroit);
//		
//		
//		Personne emmanuelle = new Personne();
//		emmanuelle.setEmail("emmanuelle@emmanuelle.fr");
//		emmanuelle.setMotDePasse(pass.encode("emmanuelle"));
//		PersonneDroit emmanuelleDroit = new PersonneDroit();
//		emmanuelleDroit.setDroit(d);
//		emmanuelleDroit.setPersonne(emmanuelle);
//		daoPersonne.save(emmanuelle);
//		daoPersonneDroit.save(emmanuelleDroit);
//		
//		
//		Personne benjamin = new Personne();
//		benjamin.setEmail("benjamin@benjamin.fr");
//		benjamin.setMotDePasse(pass.encode("benjamin"));
//		PersonneDroit benjaminDroit = new PersonneDroit();
//		benjaminDroit.setDroit(d);
//		benjaminDroit.setPersonne(benjamin);
//		daoPersonne.save(benjamin);
//		daoPersonneDroit.save(benjaminDroit);
		
		
//		Utilisateur u = daoUtilisateur.findById("karen").get();
//		u.setPassword(pass.encode("wallpaper"));
//		daoUtilisateur.save(u);
		
	}
	
	   
}
