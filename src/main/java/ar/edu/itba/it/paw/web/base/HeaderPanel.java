package ar.edu.itba.it.paw.web.base;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.domain.users.User;
import ar.edu.itba.it.paw.domain.users.UserRepositoryType;
import ar.edu.itba.it.paw.web.BaseSession;
import ar.edu.itba.it.paw.web.HomePage;
import ar.edu.itba.it.paw.web.LoginPage;
import ar.edu.itba.it.paw.web.SignupPage;

public class HeaderPanel extends Panel {
	
	@SpringBean 
	private UserRepositoryType users;

	@SuppressWarnings({ "rawtypes", "serial" })
	public HeaderPanel(String id) {
		super(id);
		initialize();
		boolean isSignIn = BaseSession.get().isSignedIn();
		Link logoutLink = new Link("logoutLink") {

			@Override
			public void onClick() {
				BaseSession.get().invalidate();
				setResponsePage(HomePage.class);
			}
			
		};
		add(logoutLink.setVisible(isSignIn));

		Link loginLink = new Link("loginLink") {

			@Override
			public void onClick() {
				setResponsePage(LoginPage.class);
			}
		};
		add(loginLink.setVisible(!isSignIn));

		Link signupLink = new Link("signupLink") {

			@Override
			public void onClick() {
				setResponsePage(SignupPage.class);
			}
		};
		add(signupLink.setVisible(!isSignIn));
		
	}

	private void initialize() {
		boolean isSignIn = BaseSession.get().isSignedIn();
		User user;
		if (isSignIn) {
			user = users.getUser(BaseSession.get().getUsername());
			System.out.println(BaseSession.get().getUsername());
		}
		
	}

}
