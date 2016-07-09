package ar.edu.itba.it.paw.web.authentication;

import java.util.Date;
import java.util.List;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.validation.EqualPasswordInputValidator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.RangeValidator;
import org.apache.wicket.validation.validator.StringValidator.MaximumLengthValidator;
import org.apache.wicket.validation.validator.StringValidator.MinimumLengthValidator;

import ar.edu.itba.it.paw.domain.address.Address;
import ar.edu.itba.it.paw.domain.address.AddressRepositoryType;
import ar.edu.itba.it.paw.domain.address.Neighborhood;
import ar.edu.itba.it.paw.domain.users.CredentialRepositoryType;
import ar.edu.itba.it.paw.domain.users.Question;
import ar.edu.itba.it.paw.domain.users.QuestionRepositoryType;
import ar.edu.itba.it.paw.domain.users.User;
import ar.edu.itba.it.paw.domain.users.UserRepositoryType;
import ar.edu.itba.it.paw.services.DateService;
import ar.edu.itba.it.paw.web.BaseSession;
import ar.edu.itba.it.paw.web.base.BasePage;

public class SignupPage extends BasePage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SpringBean
	private UserRepositoryType users;
	
	@SpringBean
	private AddressRepositoryType addresses;
	
	@SpringBean
	private CredentialRepositoryType credentials;
	
	@SpringBean
	private QuestionRepositoryType questions;
	
	//Base Information
	private String firstName;
	private String lastName;
	private Date birth; 
	private Integer day;
	private Integer month;
	private Integer year;
	
	//Authentication
	private String email;
	private String password;
	private String confirmation;
	
	//Address
	private Address address;
	private String street;
	private Integer number;
	private Integer floor;
	private String apartment;
	private Neighborhood neighborhood;
	private String province;
	private String city;
	
	//Question
	private Question question;
	private String answer;
	
	public SignupPage() {
		
		Form<SignupPage> form = new Form<SignupPage>("signupForm", new CompoundPropertyModel<SignupPage>(this)) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit() {
				BaseSession session = BaseSession.get();
				
				if (!DateService.validateBirth(year, month, day)) {
					error(getString("signup.failed.invalidDate"));
					return;
				}
				
				User user = new User();
				birth = DateService.date(year, month, day);
				user.setBirth(birth);
				user.setEmail(email);
				user.setFirstName(firstName);
				user.setLastName(lastName);
				user.setAnswer(answer);
				user.setQuestion(question);
				user.setIsAdmin(false);
				user.setManager(false);
				address = new Address(street, number, floor, apartment, neighborhood, province, city);
				user.setAddress(address);
				
				if (users.setUser(user, password) != null) {
					System.out.println("Logged");
					session.signIn(email, password, credentials, users);
					if (!continueToOriginalDestination()) { // Qué carajo hace esto??
						System.out.println("No logra cargar la página principal");
						setResponsePage(getApplication().getHomePage());
					}
				} else {
					error(getString("signup.failed.creation"));
				}
			}
		};
		
		// Basic Information
		form.add(new TextField<String>("firstName").add(new MaximumLengthValidator(User.FIRST_NAME_MAX_SIZE)).setRequired(true));
		form.add(new TextField<String>("lastName").add(new MaximumLengthValidator(User.LAST_NAME_MAX_SIZE)).setRequired(true));
		// TODO: check if this will be like this change to calendar! or validate format
//		form.add(new TextField<Date>("birth")
//				.add(DateValidator.maximum(new Date()))
//				.setRequired(true));
		
		form.add(new TextField<Integer>("day")
				.add(new RangeValidator<Integer>(1, 31))
				.setRequired(true));
		form.add(new TextField<Integer>("month")
				.add(new RangeValidator<Integer>(1, 12))
				.setRequired(true));
		form.add(new TextField<Integer>("year")
				.add(new RangeValidator<Integer>(1900, null)) //TODO: this should change for future years 
				.setRequired(true));
		
		// Authetication section
		form.add(new EmailTextField("email")
				.add(new MaximumLengthValidator(User.LAST_NAME_MAX_SIZE))
				.setRequired(true));
		PasswordTextField passwordt = (PasswordTextField) new PasswordTextField("password")
				.add(new MaximumLengthValidator(User.PASSWORD_MAX_SIZE))
				.add(new MinimumLengthValidator(User.PASSWORD_MIN_SIZE))
				.setRequired(true);
		
		PasswordTextField cpasswordt = (PasswordTextField) new PasswordTextField("confirmation")
				.setRequired(true);
		
		form.add(passwordt);
		form.add(cpasswordt);
		form.add(new EqualPasswordInputValidator(passwordt, cpasswordt));
		
		// Address 
		// TODO: update validations
		form.add(new TextField<String>("street").add(new MaximumLengthValidator(User.FIRST_NAME_MAX_SIZE)).setRequired(true));
		form.add(new TextField<Integer>("number"));
		form.add(new TextField<Integer>("floor"));
		form.add(new TextField<String>("apartment").add(new MaximumLengthValidator(User.FIRST_NAME_MAX_SIZE)));
		
		IModel<List<Neighborhood>> neighborhoodsModel = new LoadableDetachableModel<List<Neighborhood>>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected List<Neighborhood> load() {
				return addresses.getNeigh();
			}
		};
		form.add(new DropDownChoice<Neighborhood>("neighborhood", neighborhoodsModel)
				.setChoiceRenderer(new ChoiceRenderer<Neighborhood>("name", "id"))
				.setRequired(true));	
		form.add(new TextField<String>("province").add(new MaximumLengthValidator(User.FIRST_NAME_MAX_SIZE)).setRequired(true));
		form.add(new TextField<String>("city").add(new MaximumLengthValidator(User.FIRST_NAME_MAX_SIZE)).setRequired(true));
		
		//Question
		IModel<List<Question>> questionsModel = new LoadableDetachableModel<List<Question>>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected List<Question> load() {
				return questions.getQuestions();
			}
		};
		form.add(new DropDownChoice<Question>("question", questionsModel)
				.setChoiceRenderer(new ChoiceRenderer<Question>("question", "id"))
				.setRequired(true));
		form.add(new TextField<String>("answer").add(new MaximumLengthValidator(User.LAST_NAME_MAX_SIZE)).setRequired(true));
		
		form.add(new Button("signup", new ResourceModel("signup.submit.label")));
		form.add(new FeedbackPanel("feedback"));
		add(form);
	}

}
