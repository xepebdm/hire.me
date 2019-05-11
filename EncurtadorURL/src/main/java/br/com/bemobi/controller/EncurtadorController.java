package br.com.bemobi.controller;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.bemobi.model.Url;
import br.com.bemobi.service.EncurtadorService;

@RestController("/")
public class EncurtadorController {

	@Autowired
	private EncurtadorService service;

	@PutMapping("create")
	public ResponseEntity<?> saveUrl(@RequestParam(required = true) String urlFull,
			@RequestParam(required = false) String alias) {

		LocalTime time = LocalTime.now();

		Url url = new Url();
		url.setUrlFull(urlFull);
		url.setAlias(alias);
        url.setTempo(time.toString());
        
		service.salvar(url);

		return new ResponseEntity<>(url, HttpStatus.OK);
	}
}
