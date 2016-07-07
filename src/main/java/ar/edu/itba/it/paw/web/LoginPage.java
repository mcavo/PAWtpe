package ar.edu.itba.it.paw.web;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.StringValidator.MaximumLengthValidator;

import ar.edu.itba.it.paw.domain.users.CredentialRepositoryType;
import ar.edu.itba.it.paw.domain.users.User;
import ar.edu.itba.it.paw.web.base.BasePage;

public class LoginPage extends BasePage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SpringBean
	public CredentialRepositoryType credentials;

	private String email;
	private String password;

	public LoginPage() {
		
		Form<LoginPage> form = new Form<LoginPage>("loginForm", new CompoundPropertyModel<LoginPage>(this)) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit() {
				BaseSession session = BaseSession.get();
				if (session.signIn(email, password, credentials)) {
					System.out.println("Logged");
					if (!continueToOriginalDestination()) {
						System.out.println("No logra cargar la página principal");
						setResponsePage(getApplication().getHomePage());
					}
				} else {
					error(getString("login.failed.badcredentials"));
				}
			}
		};
		
		form.add(new EmailTextField("email").add(new MaximumLengthValidator(User.EMAIL_MAX_SIZE)).setRequired(true));
		form.add(new PasswordTextField("password").add(new MaximumLengthValidator(User.PASSWORD_MAX_SIZE))
				.setRequired(true));
		form.add(new Button("submit", new ResourceModel("submit")));
		add(new FeedbackPanel("feedback"));
		add(form);
	}

}