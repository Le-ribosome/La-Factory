package fr.projet.lafactory.restcontroller;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UserRestController.class)
public class UserRestControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testAll() throws Exception {

		try {
			mockMvc.perform(get("/all")).andExpect(status().isOk()).andExpect(view().name("all"));

		} catch (Exception e) {
			fail("Liste des personnes non validée.");
		}
	}
	
}
