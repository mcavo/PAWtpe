package ar.edu.itba.it.paw.exceptions;

import ar.edu.itba.it.paw.domain.users.Credential;

public class DuplicateEmailException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Credential credential;
	
	public DuplicateEmailException() {
	}

	public DuplicateEmailException(Credential credential) {
		this.credential = credential;
	}
	
	public Credential getCredential() {
		return credential;
	}
}
