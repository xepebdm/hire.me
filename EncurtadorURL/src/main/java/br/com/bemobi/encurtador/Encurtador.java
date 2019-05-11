package br.com.bemobi.encurtador;

/**
 * Classe responsavel por realizar a lógica para encurtar a URL
 * 
 * @author Alexsandre Rodrigues
 *
 */
public class Encurtador {

	/**
	 * Metodo que gera 5 (cinco) caracteres randomicamente para ser a URL encurtada.
	 * 
	 * @return String
	 */
	public String getRandomChars() {
		String randomStr = "";
		String possibleChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		
		for(int i = 0; i< 5; i++) {
			randomStr += possibleChars.charAt(
					(int) Math.floor(Math.random() * possibleChars.length()));
		}
		
		return randomStr;
	}
}
