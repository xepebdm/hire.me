package br.com.bemobi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Classe de exception quando o ALIAS da URL não for encontada
 * 
 * @author Alexsandre Rodrigues
 *
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "SHORTENED URL NOT FOUND")
public class UrlNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private final String ERR_CODE = "002";
	private final String Description = "SHORTENED URL NOT FOUND";
	
	
	public String getERR_CODE() {
		return ERR_CODE;
	}
	public String getDescription() {
		return Description;
	}
	
	
	

}
