package ar.edu.itba.it.paw.web;

import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

import ar.edu.itba.it.paw.domain.users.Credential;
import ar.edu.itba.it.paw.domain.users.CredentialRepositoryType;
import ar.edu.itba.it.paw.domain.users.User;
import ar.edu.itba.it.paw.domain.users.UserRepositoryType;
import ar.edu.itba.it.paw.exceptions.CredentialNoMatchException;
import ar.edu.itba.it.paw.services.AuthenticationService;

public class BaseSession extends WebSession {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;

	public static BaseSession get() {
		return (BaseSession) Session.get();
	}

	public BaseSession(Request request) {
		super(request);
	}

	public User getUser() {
		return user;
	}

	public boolean[] signIn(String username, String password, CredentialRepositoryType credentials, UserRepositoryType users) {
		try {
			Credential credential = credentials.getCredentials(username, password);
			this.user = users.getUser(credential);
			boolean changePassword = AuthenticationService.getInstance().userNeedsUpdatePassword(credential);
			boolean[] ans = {true, changePassword};
			return ans;
		} catch(CredentialNoMatchException e) {
			boolean[] ans = {false, false};
			return ans;
		}
	}
	
	public void updateUser(User user) {
		this.user = user;
	}
	
	public boolean isSignedIn() {
		return user != null;
	}

	public void signOut() {
        invalidate();
        clear();
	}
}
