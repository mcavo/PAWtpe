package ar.edu.itba.it.paw.exceptions;

import ar.edu.itba.it.paw.models.Credential;

public class DuplicateEmailException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Credential credential;
	
	public DuplicateEmailException() {
		// TODO Auto-generated constructor stub
	}

	public DuplicateEmailException(Credential credential) {
		this.credential = credential;
	}
	
	public Credential getCredential() {
		return credential;
	}
}
