package br.com.bemobi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bemobi.encurtador.Encurtador;
import br.com.bemobi.exception.UrlBadRequestException;
import br.com.bemobi.exception.UrlNotFoundException;
import br.com.bemobi.model.Url;
import br.com.bemobi.repository.UrlRepository;

/**
 * 
 * @author Alexsandre Rodrigues
 *
 */

@Service
public class EncurtadorService {

	@Autowired
	private UrlRepository repository;

	
	/**
	 * Metodo para salvar uma URL
	 * 
	 * @param url
	 */
	public void salvar(Url url) {
		long time = System.currentTimeMillis();

		validar(url);

		long inicio = Long.valueOf(url.getTempo());
		long tempo = time - inicio;

		url.setTempo(tempo);
		url.setUrlShort("http://localhost:8080/" + url.getAlias());
		repository.save(url);
	}

	
	/**
	 * Metodo privado para validar alguns campos da Url.
	 * Primeiro é feita a verificação do prefixo Https:// pois o HttpServletResponse utiliza esse prefixo para redirecionar o usuario corretamente.
	 * Depois é feita a verificação do ALIAS, caso o usuário não informe um ALIAS a aplicação irá gerar um aleatoriamente, caso o usuário tenha informado um ALIAS é feito uma verificação na base de dados
	 * para saber se o ALIAS informado já existe, se existir será lançado uma exception.
	 * 
	 * @param url
	 * @throws UrlBadRequestException
	 */
	private void validar(Url url) {

		// validação da URL FULL para que contenha o prefixo https ou http, do contrario o usuario não será redirecionado corretamente
		if(!url.getUrlFull().contains("https://") && !url.getUrlFull().contains("http://")) {
			url.setUrlFull("https://" + url.getUrlFull());
		}
		
		
		// se o ALIAS for nulo a aplicação irá criar um randomicamente
		if (url.getAlias() == null) {

			Encurtador enc = new Encurtador();
			url.setAlias(enc.getRandomChars());

		} else {

			Url newUrl = repository.findByAlias(url.getAlias());

			if (newUrl != null) {
				throw new UrlBadRequestException(url.getAlias());
			}
		}
	}

	
	/**
	 * Método público que recebe um ALIAS e retorna o objeto URL.
	 * Caso não exista o ALIAS informado será lançado uma exception
	 * @param alias
	 * @return Url
	 * @throws UrlNotFoundException
	 */
	public Url encontrar(String alias) {
		Url url = null;
		url = repository.findByAlias(alias);

		if (url == null) {
			throw new UrlNotFoundException();
		}
		
		return url;
	}

	
	/**
	 * Deletar todos do banco de dados
	 */
	public void deleteAll() {
		repository.deleteAll();
	}


	public List<Url> encontreTodas() {
		return repository.findAll();
	}
}
