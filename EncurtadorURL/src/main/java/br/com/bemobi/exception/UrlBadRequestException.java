package br.com.bemobi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Classe responsavel por lançar a exception quando o ALIAS já existir na base de dados
 * 
 * @author Alexsandre Rodrigues
 *
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "CUSTOM ALIAS ALREADY EXISTS")
public class UrlBadRequestException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private String alias;
	private final String ERR_CODE = "001";
	private final String Description = "CUSTOM ALIAS ALREADY EXISTS";
	
	public UrlBadRequestException(String alias) {
		this.alias = alias;
	}
	
	public String getAlias() {
		return alias;
	}
	
	public String getERR_CODE() {
		return ERR_CODE;
	}
	public String getDescription() {
		return Description;
	}

}
