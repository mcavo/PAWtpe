package ar.edu.itba.it.paw.Exceptions;

public class NoCredentialException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String email;
	private int id;
	
	public NoCredentialException() {}
	
	public NoCredentialException(String email) {
		this.email = email;
	}
	
	public NoCredentialException(int id) {
		this.id = id;
	}
	
	public String getEmail(){
		return email;
	}
	
	public int getId(){
		return id;
	}
}
