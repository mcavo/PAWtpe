package ar.edu.itba.it.paw.web.authentication.password;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.validation.EqualPasswordInputValidator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.StringValidator.MaximumLengthValidator;
import org.apache.wicket.validation.validator.StringValidator.MinimumLengthValidator;

import ar.edu.itba.it.paw.domain.users.CredentialRepositoryType;
import ar.edu.itba.it.paw.domain.users.User;
import ar.edu.itba.it.paw.web.HomePage;
import ar.edu.itba.it.paw.web.base.BasePage;

@SuppressWarnings("serial")
public class AskQuestionPage extends BasePage {

	@SpringBean private CredentialRepositoryType credentials;
	
	private String answer;
	private String newPassword;
	private String confirmation;
	
	public AskQuestionPage(final User user) {
		
		Form<AskQuestionPage> form = new Form<AskQuestionPage>(
				"askQuestionForm", new CompoundPropertyModel<AskQuestionPage>(this)) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit() {
				User updatedUser = users.getUser(user.getEmail());
				if (updatedUser == null || !updatedUser.getAnswer().equals(answer)) {
					error(getString("askQuestion.wrongAnswer"));
				} else {
					credentials.setPwd(newPassword, updatedUser.getId());
					setResponsePage(new HomePage());
				}
			}
		};
		
		PasswordTextField passwordt = (PasswordTextField) new PasswordTextField("newPassword")
				.add(new MaximumLengthValidator(User.PASSWORD_MAX_SIZE))
				.add(new MinimumLengthValidator(User.PASSWORD_MIN_SIZE))
				.setRequired(true);
		
		PasswordTextField cpasswordt = (PasswordTextField) new PasswordTextField("confirmation")
				.setRequired(true);
		
		form.add(new Label("question", user.getQuestion().getQuestion()));
		
		form.add(passwordt);
		form.add(cpasswordt);
		form.add(new EqualPasswordInputValidator(passwordt, cpasswordt));
		
		form.add(new TextField<String>("answer"));
		
		form.add(new FeedbackPanel("feedback"));
		form.add(new Button("send", new ResourceModel("send")));
		add(form);
	}
}
