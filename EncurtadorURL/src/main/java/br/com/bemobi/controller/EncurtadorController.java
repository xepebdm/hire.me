package br.com.bemobi.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.bemobi.exception.ApiError;
import br.com.bemobi.exception.UrlBadRequestException;
import br.com.bemobi.exception.UrlNotFoundException;
import br.com.bemobi.model.Url;
import br.com.bemobi.service.EncurtadorService;

@RestController("/")
public class EncurtadorController {

	@Autowired
	private EncurtadorService service;

	@PutMapping("create")
	public ResponseEntity<?> saveUrl(@RequestParam(required = true) String url,
			@RequestParam(required = false) String alias) {

		Long time = System.currentTimeMillis();

		Url urlNew = new Url();
		urlNew.setUrlFull(url);
		urlNew.setAlias(alias);
        urlNew.setTempo(time);
        
		service.salvar(urlNew);

		return new ResponseEntity<>(urlNew, HttpStatus.CREATED);
	}
	
	@GetMapping("{alias}")
	public ResponseEntity<?> findUrl(HttpServletResponse response,
			@PathVariable("alias") String alias) throws IOException, URISyntaxException{
		Url url = service.encontrar(alias);
		
		URI newPage = new URI(url.getUrlFull());
	    HttpHeaders httpHeaders = new HttpHeaders();
	    httpHeaders.setLocation(newPage);
	    return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);

	}
	
	@GetMapping
	public ResponseEntity<?> getAll(){
		List<Url> urls = service.encontreTodas();
		
		return new ResponseEntity<>(urls, HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteAll(){
		service.deleteAll();
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	/**
	 * Tratamento de exception quando o ALIAS não existir no banco de dados
	 * @param urlNotFoundEx
	 * @return ApiError
	 */
	@ExceptionHandler({UrlNotFoundException.class})
	protected ResponseEntity<Object> handleEntityNotFound(UrlNotFoundException urlNotFoundEx) {
		ApiError apiError = new ApiError(urlNotFoundEx.getERR_CODE(), urlNotFoundEx.getDescription());
		apiError.setAlias("");
		
		return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);

	}
	
	/**
	 * Tratamento de exception quando o usuário for cadastrar um ALIAS que já existe no banco de dados
	 * @param urlBadRequestEx
	 * @return ApiError
	 */
	@ExceptionHandler({UrlBadRequestException.class})
	protected ResponseEntity<Object> handleEntityBadRequest(UrlBadRequestException urlBadRequestEx) {
		ApiError apiError = new ApiError(urlBadRequestEx.getAlias(), urlBadRequestEx.getERR_CODE(), urlBadRequestEx.getDescription());
		
		return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);

	}
}
