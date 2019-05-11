package br.com.bemobi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Classe de exception quando a URL não encontada
 * 
 * @author Alexsandre Rodrigues
 *
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "SHORTENED URL NOT FOUND")
public class UrlNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

}
