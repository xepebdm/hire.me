package br.com.bemobi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		repository.save(url);
	}
}
