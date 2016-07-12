package ar.edu.itba.it.paw.web.authentication.password;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.validation.EqualPasswordInputValidator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.StringValidator.MaximumLengthValidator;
import org.apache.wicket.validation.validator.StringValidator.MinimumLengthValidator;

import ar.edu.itba.it.paw.domain.users.Credential;
import ar.edu.itba.it.paw.domain.users.CredentialRepositoryType;
import ar.edu.itba.it.paw.domain.users.User;
import ar.edu.itba.it.paw.exceptions.CredentialNoMatchException;
import ar.edu.itba.it.paw.web.HomePage;
import ar.edu.itba.it.paw.web.base.BasePage;

@SuppressWarnings("serial")
public class UpdatePasswordPage extends BasePage {

	@SpringBean private CredentialRepositoryType credentials;
	
	private String newPassword;
	@SuppressWarnings("unused")
	private String confirmation;
	private String oldPassword;
	
	public UpdatePasswordPage() {
		
		Form<UpdatePasswordPage> form = new Form<UpdatePasswordPage>(
				"updatePasswordForm", new CompoundPropertyModel<UpdatePasswordPage>(this)) {

			@Override
			protected void onSubmit() {
				if (newPassword.equals(oldPassword)) {
					error(getString("updatePassword.equalsPassword"));
				}

				try {
					Credential credential = credentials.getCredentials(loggedUser.getEmail(), oldPassword);
					credential.updatePassword(newPassword);
					setResponsePage(new HomePage());
				} catch (CredentialNoMatchException e) {
					error(getString("updatePassword.invalidPassword"));
				}
			}
		};
		
		PasswordTextField oldPassword = (PasswordTextField) new PasswordTextField("oldPassword")
				.add(new MaximumLengthValidator(User.PASSWORD_MAX_SIZE))
				.add(new MinimumLengthValidator(User.PASSWORD_MIN_SIZE))
				.setRequired(true);
		
		PasswordTextField passwordt = (PasswordTextField) new PasswordTextField("newPassword")
				.add(new MaximumLengthValidator(User.PASSWORD_MAX_SIZE))
				.add(new MinimumLengthValidator(User.PASSWORD_MIN_SIZE))
				.setRequired(true);
		
		PasswordTextField cpasswordt = (PasswordTextField) new PasswordTextField("confirmation")
				.setRequired(true);
		
		form.add(oldPassword);
		form.add(passwordt);
		form.add(cpasswordt);
		form.add(new EqualPasswordInputValidator(passwordt, cpasswordt));
		
		form.add(new FeedbackPanel("feedback"));
		form.add(new Button("send", new ResourceModel("updatePassword.update")));
		add(form);
	}
}
