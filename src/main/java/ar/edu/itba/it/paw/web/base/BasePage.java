package ar.edu.itba.it.paw.web.base;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.domain.users.User;
import ar.edu.itba.it.paw.domain.users.UserRepositoryType;
import ar.edu.itba.it.paw.web.BaseSession;

public class BasePage extends WebPage {
	
	private static final long serialVersionUID = 1L;
	
	@SpringBean 
	protected UserRepositoryType users;
	
	protected User loggedUser;
	
	public BasePage() {
		initialize();
		add(new HeaderPanel("headerPanel"));
	}
	
	private void initialize() {
		boolean isSignIn = BaseSession.get().isSignedIn();
		if (isSignIn) {
			loggedUser = users.getUser(BaseSession.get().getUsername());
		}
		
	}
	
}