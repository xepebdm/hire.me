package br.com.bemobi.service;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.bemobi.exception.UrlBadRequestException;
import br.com.bemobi.exception.UrlNotFoundException;
import br.com.bemobi.model.Url;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EncurtadorServiceTest {


	@Autowired
	private EncurtadorService service;
	
	//variaveis utilizadas nos metodos de testes
	Url url = new Url();
	Url url2 = new Url();
	Url url3 = new Url();

	@Before
	public void setUp() {

		Long time = System.currentTimeMillis();
		url.setUrlFull("https://www.google.com");
		url.setAlias("teste");
        url.setTempo(time);
        

		url2.setUrlFull("https://www.facebook.com");
        url2.setTempo(time);
        
		url3.setUrlFull("http://www.github.com");
		url3.setAlias("git");
        url3.setTempo(time);
	}
	
	@Test
	public void testAddSalvarUrl() {
		service.salvar(url);
	}
	
	@Test(expected = UrlBadRequestException.class)
	public void testComAliasJaExistente() {
		Long time = System.currentTimeMillis();
		Url url4 = new Url();
		url4.setUrlFull("https://www.google.com");
		url4.setAlias("teste");
		url4.setTempo(time);
		
		service.salvar(url);
		service.salvar(url4);
	}
	
	@Test
	public void testEncontrarAlias() {
		service.salvar(url3);
		
		Url url = service.encontrar("git");
		
		assertEquals("http://www.github.com", url.getUrlFull());
	}
	
	@Test(expected = UrlNotFoundException.class)
	public void testEncontrarComAliasNotFound() {
		service.encontrar("I8Vj5");
	}
	
	@After
	public void tearDown() {
		service.deleteAll();
	}

}
