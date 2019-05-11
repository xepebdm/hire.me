package br.com.bemobi.service;

import java.time.LocalTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.bemobi.exception.UrlBadRequestException;
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
		LocalTime time = LocalTime.now();

		url.setUrlFull("www.google.com");
		url.setAlias("teste");
        url.setTempo(time.toString());
        

		url2.setUrlFull("www.facebook.com");
        url2.setTempo(time.toString());
        
		url3.setUrlFull("www.github.com");
		url3.setAlias("git");
        url3.setTempo(time.toString());
	}
	
	@Test
	public void testSalvarUrl() {
		service.salvar(url);
	}
	
	@Test(expected = UrlBadRequestException.class)
	public void testComAliasJaExistente() {
		LocalTime time = LocalTime.now();
		Url url4 = new Url();
		url4.setUrlFull("www.google.com");
		url4.setAlias("teste");
		url4.setTempo(time.toString());
		
		service.salvar(url4);
	}
	
	@After
	public void tearDown() {
		service.deleteAll();
	}

}
