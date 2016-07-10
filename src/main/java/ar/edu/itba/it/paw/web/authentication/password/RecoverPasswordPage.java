package ar.edu.itba.it.paw.web.authentication.password;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.validation.validator.StringValidator.MaximumLengthValidator;

import ar.edu.itba.it.paw.domain.users.User;
import ar.edu.itba.it.paw.web.base.BasePage;

public class RecoverPasswordPage extends BasePage {

	private String email;
	
	public RecoverPasswordPage() {
		Form<RecoverPasswordPage> form = new Form<RecoverPasswordPage>(
				"recoverPasswordForm", new CompoundPropertyModel<RecoverPasswordPage>(this)) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit() {
				User user = users.getUser(email);
				if (user == null) {
					error(getString("login.failed.badcredentials"));
				} else {
					setResponsePage(new AskQuestionPage(user));
				}
			}
		};
		
		form.add(new EmailTextField("email").add(new MaximumLengthValidator(User.EMAIL_MAX_SIZE)).setRequired(true));
		form.add(new Button("continue", new ResourceModel("continue")));
		add(form);

	}

}
