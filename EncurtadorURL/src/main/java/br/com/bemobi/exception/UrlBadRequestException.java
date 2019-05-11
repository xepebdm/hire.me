package br.com.bemobi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Classe responsavel por lançar a exception quando o ALIAS já existir na base de dados
 * 
 * @author Alexsandre Rodrigues
 *
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "CUSTOM ALIAS ALREADY EXISTS")
public class UrlBadRequestException extends RuntimeException {

	private static final long serialVersionUID = 1L;

}
