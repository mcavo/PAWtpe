package ar.edu.itba.it.paw.web;

import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

public class DemoWicketSession extends WebSession {

	private static final long serialVersionUID = 1L;

	private String mail;

	public static DemoWicketSession get() {
		return (DemoWicketSession) Session.get();
	}

	public DemoWicketSession(Request request) {
		super(request);
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

//	public boolean signIn(String mail, String password, UserRepo users) {
//		User user = users.get(mail);
//		if (user != null && user.checkPass(password)) {
//			this.mail = mail;
//			return true;
//		}
//		return false;
//	}

	public boolean isSigned() {
		return mail != null;
	}

	public void signOut() {
		this.mail = null;
		invalidate();
		clear();
	}

}
