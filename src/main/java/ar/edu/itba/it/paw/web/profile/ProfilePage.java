package ar.edu.itba.it.paw.web.profile;

import java.util.Date;
import java.util.List;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.RangeValidator;
import org.apache.wicket.validation.validator.StringValidator.MaximumLengthValidator;

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

public class ProfilePage extends BasePage {

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
	
	private String firstName;
	private String lastName;
	private String email;
	private Date birth; 
	private Integer day;
	private Integer month;
	private Integer year;
	private Address address;
	private String street;
	private Integer number;
	private Integer floor;
	private String apartment;
	private Neighborhood neighborhood;
	private String province;
	private String city;
	private Question question;
	private String answer;
	
	public ProfilePage() {
		
		firstName = loggedUser.getFirstName();
		lastName = loggedUser.getLastName();
		email = loggedUser.getEmail();
		birth = loggedUser.getBirth();
		day = DateService.getDayOfMonth(loggedUser.getBirth());
		month = DateService.getMonth(loggedUser.getBirth());
		year = DateService.getYear(loggedUser.getBirth());
		address = loggedUser.getAddress();
		street = loggedUser.getAddress().getStreet();
		number = loggedUser.getAddress().getNumber();
		floor = loggedUser.getAddress().getFloor();
		apartment = loggedUser.getAddress().getApartment();
		neighborhood = loggedUser.getAddress().getNeighborhood();
		province = loggedUser.getAddress().getProvince();
		city = loggedUser.getAddress().getCity();
		question = loggedUser.getQuestion();
		answer = loggedUser.getAnswer();
			
			Form<ProfilePage> form = new Form<ProfilePage>("signupForm", new CompoundPropertyModel<ProfilePage>(this)) {
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
					
					User user = users.getUser(loggedUser.getEmail());
					birth = DateService.date(year, month, day);
					user.setBirth(birth);
					user.setEmail(email);
					user.setFirstName(firstName);
					user.setLastName(lastName);
					user.setAnswer(answer);
					user.setQuestion(question);
					user.setIsAdmin(false);
					user.setManager(false);
					user.getAddress().setStreet(street);
					user.getAddress().setNumber(number);
					user.getAddress().setFloor(floor);
					user.getAddress().setApartment(apartment);
					user.getAddress().setNeighborhood(neighborhood);
					user.getAddress().setProvince(province);
					user.getAddress().setCity(city);
					
					if (users.updateUser(user) != null) {
						setResponsePage(getApplication().getHomePage());
					} else {
						error(getString("profile.failed.creation"));
					}
					
				}
			};
			
			// Basic Information
			form.add(new TextField<String>("firstName").add(new MaximumLengthValidator(User.FIRST_NAME_MAX_SIZE)).setRequired(true));
			form.add(new TextField<String>("lastName").add(new MaximumLengthValidator(User.LAST_NAME_MAX_SIZE)).setRequired(true));
			// TODO: check if this will be like this change to calendar! or validate format
//			form.add(new TextField<Date>("birth")
//					.add(DateValidator.maximum(new Date()))
//					.setRequired(true));
			
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
			
			form.add(new Button("update", new ResourceModel("signup.submit.label")));
			form.add(new FeedbackPanel("feedback"));
			add(form);
	}

}
