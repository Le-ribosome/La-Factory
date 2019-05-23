package fr.projet.lafactory.restcontroller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import fr.projet.lafactory.LaFactoryApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = LaFactoryApplication.class)
@WebAppConfiguration
public class UserRestControllerTest {

	private MockMvc mockMvc;

	@Autowired
	WebApplicationContext webApplicationContext;

	@Before
	public void beforeTest() {
		assertNotNull(webApplicationContext);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testAll() throws Exception {

		try {
			mockMvc.perform(get("/rest/user/all")).andExpect(status().isOk())/*.andExpect(view().name("home"))*/;

		} catch (Exception e) {
			fail("Liste des personnes non validée.");
		}
	}

}
