package fr.projet.lafactory.dao;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import fr.projet.lafactory.model.Stagiaire;

@RunWith(SpringRunner.class) // Test dans le contexte de Spring
public class DAOStagiaireTest {

	@Autowired(required = false)
	private IDAOStagiaire daoStagiaire;

	@Before
	public void beforeTest() {
		assertNotNull(daoStagiaire);
	}
	
	@Test
	@Transactional
	@Rollback
	public void testAdd() {
		Stagiaire stagiaire = new Stagiaire();
		
		stagiaire.setEmail("adresse@laformation.fr");
		stagiaire.setMotDePasse("ViveLaFormation");
		
		try {
			daoStagiaire.save(stagiaire);
			assertNotEquals(0, stagiaire.getId());
		}
		catch (Exception e) {
			fail("Le stagiaire ne s'est pas enregisté.");
		}
	}
	
	// TODO Il faut au moins un stagiaire dans la BDD
	@Test
	public void testFindAll() {
		System.out.println("Coucou");
		assertNotEquals(0, daoStagiaire.findAll().size());
	}
	
	
}
