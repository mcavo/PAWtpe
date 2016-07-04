package ar.edu.itba.it.paw.web;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.domain.users.CredentialRepositoryType;
import ar.edu.itba.it.paw.exceptions.CredentialNoMatchException;

public class Login extends WebPage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@SpringBean
	public CredentialRepositoryType credentials;
	
	public Login() {
		final LoginForm form = new LoginForm("loginForm", credentials);
		add(form);
	}

	private static class LoginForm extends StatelessForm {

		private static final long serialVersionUID = -6826853507535977683L;
		
		private CredentialRepositoryType credentials;
		
		private String username;
		private String password;

		public LoginForm(String id, CredentialRepositoryType credentails) {
			super(id);
			this.credentials = credentails;
			setModel(new CompoundPropertyModel(this));
			add(new Label("usernameLabel", getString("login.username.label", null, "Username")));
			add(new RequiredTextField("username"));
			add(new Label("passwordLabel", getString("login.password.label", null, "Password")));
			add(new PasswordTextField("password"));
			add(new FeedbackPanel("feedback"));
		}

		@Override
		protected void onSubmit() {
			BaseSession session = BaseSession.get();

			if (session.signIn(username, password, credentials)) {
				System.out.println("Logged");
				if (!continueToOriginalDestination()) { // Qué carajo hace esto??
					System.out.println("No logra cargar la página principal");
					setResponsePage(getApplication().getHomePage());
				}
			} else {
				error(getString("login.failed.badcredentials"));
			}
		}

		private void setDefaultResponsePageIfNecessary() {
			if (!continueToOriginalDestination()) {
				setResponsePage(getApplication().getHomePage());
			}
		}
	}

}