package fr.projet.lafactory.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import fr.projet.lafactory.LaFactoryApplication;
import fr.projet.lafactory.model.Stagiaire;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { LaFactoryApplication.class }, webEnvironment = WebEnvironment.DEFINED_PORT)
public class DAOStagiaireTest {

	@Autowired(required = false)
	private IDAOStagiaire daoStagiaire;

	@Before
	public void beforeTest() {
		assertNotNull(daoStagiaire);
	}

	// TODO Il faut au moins un stagiaire dans la BDD

	@Test
	public void testFindAll() {
		System.out.println("Coucou");
		assertNotEquals(0, daoStagiaire.findAll().size());
	}

		// CReate
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
		} catch (Exception e) {
			fail("Le stagiaire ne s'est pas enregisté.");
		}
	}
	
		// Update

	@Test
	@Transactional
	@Rollback
	public void testUpdate() {
		Stagiaire stagiaire = new Stagiaire();

		stagiaire.setEmail("adresse@laformation.fr");
		stagiaire.setMotDePasse("ViveLaFormation");
		daoStagiaire.save(stagiaire);

		stagiaire.setEmail("adresse2");

		try {
			daoStagiaire.save(stagiaire);
			assertEquals("adresse2", daoStagiaire.findById(stagiaire.getId()).get().getEmail());
		} catch (Exception e) {
			fail("Le stagiaire n'a pas été modifié.");
		}
	}
	
		// Delete
	@Test
	public void testDelete() {
		Stagiaire stagiaire = new Stagiaire();

		stagiaire.setEmail("adresse@laformation.fr");
		stagiaire.setMotDePasse("ViveLaFormation");

		daoStagiaire.save(stagiaire);

		try {
			daoStagiaire.delete(stagiaire);
			assertFalse(daoStagiaire.findById(stagiaire.getId()).isPresent());
		} catch (Exception e) {
			fail("Le stagiaire n'a pas été supprimé");
		}

	}


}
