package br.com.bemobi.exception;

public class ApiError {

	private String alias;
	private String ERR_CODE;
	private String Description;
	
	public ApiError(String alias, String error, String description) {
		this.alias = alias;
		ERR_CODE = error;
		Description = description;
		
	}
	
	public ApiError(String error, String description) {
		ERR_CODE = error;
		Description = description;
	}
	
	
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getERR_CODE() {
		return ERR_CODE;
	}
	public void setERR_CODE(String eRR_CODE) {
		ERR_CODE = eRR_CODE;
	}
	public String getDescription() {
		return this.Description;
	}
	public void setDescription(String description) {
		this.Description = description;
	}
	
	
}
