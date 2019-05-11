package br.com.bemobi.service;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bemobi.encurtador.Encurtador;
import br.com.bemobi.exception.UrlBadRequestException;
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

	public void salvar(Url url) {
		LocalTime time = LocalTime.now();

		validar(url);

		LocalTime inicio = LocalTime.parse(url.getTempo());
		int tempo = time.getSecond() - inicio.getSecond();

		url.setTempo(String.valueOf(tempo));
		url.setUrlShort("http://localhost:8080/" + url.getAlias());
		repository.save(url);
	}

	private void validar(Url url) {

		if (url.getAlias() == null) {

			Encurtador enc = new Encurtador();
			url.setAlias(enc.getRandomChars());

		} else {

			Url newUrl = repository.findByAlias(url.getAlias());

			if (newUrl != null) {
				throw new UrlBadRequestException();
			}
		}
	}

	public void deleteAll() {
		repository.deleteAll();
	}
}
