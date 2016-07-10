package ar.edu.itba.it.paw.web.authentication;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.StringValidator.MaximumLengthValidator;

import ar.edu.itba.it.paw.domain.users.CredentialRepositoryType;
import ar.edu.itba.it.paw.domain.users.User;
import ar.edu.itba.it.paw.domain.users.UserRepositoryType;
import ar.edu.itba.it.paw.web.BaseSession;
import ar.edu.itba.it.paw.web.HomePage;
import ar.edu.itba.it.paw.web.authentication.password.RecoverPasswordPage;
import ar.edu.itba.it.paw.web.authentication.password.UpdatePasswordPage;
import ar.edu.itba.it.paw.web.base.BasePage;

@SuppressWarnings("serial")
public class LoginPage extends BasePage {

	@SpringBean
	public CredentialRepositoryType credentials;
	
	@SpringBean
	public UserRepositoryType users;

	private String email;
	private String password;

	public LoginPage() {
		
		Form<LoginPage> form = new Form<LoginPage>("loginForm", new CompoundPropertyModel<LoginPage>(this)) {

			@Override
			protected void onSubmit() {
				BaseSession session = BaseSession.get();
				boolean[] sessionAns = session.signIn(email, password, credentials, users); 
				if (sessionAns[0]) {
					if (sessionAns[1]) {
						setResponsePage(UpdatePasswordPage.class);
						return;
					}
					if (!continueToOriginalDestination()) {
						setResponsePage(HomePage.class);
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
		add(new Link<Void>("recoverPassword") {
			@Override
			public void onClick() {
				setResponsePage(new RecoverPasswordPage());
			}
		});
	}

}