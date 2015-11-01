package ar.edu.itba.it.paw.exceptions;

public class CredentialNoMatchException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String email;
	private String pwd;
	
	public CredentialNoMatchException() {
		// TODO Auto-generated constructor stub
	}
	
	public CredentialNoMatchException(String email, String pwd) {
		this.email = email;
		this.pwd = pwd;
	}
	
	public String getEamil() {
		return email;
	}
	
	public String getPwd() {
		return pwd;
	}
}
