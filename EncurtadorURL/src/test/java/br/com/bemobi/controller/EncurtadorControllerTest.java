package br.com.bemobi.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class EncurtadorControllerTest {


	@Autowired
	private MockMvc mock;
	
	@Test
	public void testPutUrl() throws Exception{

		mock.perform(put("/create").param("url", "https://www.g1.globo.com")
				.param("alias", "globo").contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.alias").value("globo"))
		.andExpect(jsonPath("$.urlShort").value("http://localhost:8080/globo"))
		.andExpect(status().is2xxSuccessful());
	}
	
	@Test
	public void testPutSemAlias() throws Exception{

		mock.perform(put("/create").param("url", "www.linkedin.com")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.urlFull").value("https://www.linkedin.com"))
		.andExpect(status().is2xxSuccessful());
	}
	
	@Test
	public void testPutComAliasDuplicado() throws Exception{
		mock.perform(put("/create").param("url", "https://www.netflix.com")
				.param("alias", "netflix").contentType(MediaType.APPLICATION_JSON));
		
		mock.perform(put("/create").param("url", "https://www.youtube.com")
				.param("alias", "netflix").contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.err_CODE").value("001"))
		.andExpect(jsonPath("$.description").value("CUSTOM ALIAS ALREADY EXISTS"))
		.andExpect(status().is4xxClientError());
	}
	
	
	@Test
	public void testObterComAlias() throws Exception{
		mock.perform(put("/create").param("url", "https://www.g1.globo.com")
				.param("alias", "globo").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().is2xxSuccessful());
		
		mock.perform(get("/{alias}", "globo")).andExpect(status().is3xxRedirection());
	}
	
	@Test
	public void testGetComAliasInexistente() throws Exception{
		mock.perform(get("/{alias}", "h1W5R")).andExpect(status().is4xxClientError())
		.andExpect(jsonPath("$.err_CODE").value("002"))
		.andExpect(jsonPath("$.description").value("SHORTENED URL NOT FOUND"));
	}
	
	@Test
	public void testObterTodos() throws Exception{
		mock.perform(get("/")).andExpect(status().is2xxSuccessful());
	}
	
	@After
	public void tearDown() throws Exception{
		mock.perform(delete("/")).andExpect(status().is2xxSuccessful());
	}

}
