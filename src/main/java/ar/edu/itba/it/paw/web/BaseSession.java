package ar.edu.itba.it.paw.web;

import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

import ar.edu.itba.it.paw.domain.users.CredentialRepositoryType;
import ar.edu.itba.it.paw.exceptions.CredentialNoMatchException;

public class BaseSession extends WebSession {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;

	public static BaseSession get() {
		return (BaseSession) Session.get();
	}

	public BaseSession(Request request) {
		super(request);
	}

	public String getUsername() {
		return username;
	}

	public boolean signIn(String username, String password, CredentialRepositoryType credentials) {
		try {
			credentials.getCredentials(username, password);
			this.username = username;
			return true;
		} catch(CredentialNoMatchException e) {
			return false;
		}
	}

	public boolean isSignedIn() {
		return username != null;
	}

	public void signOut() {
        invalidate();
        clear();
	}
}
