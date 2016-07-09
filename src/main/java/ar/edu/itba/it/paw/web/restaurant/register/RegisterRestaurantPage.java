package ar.edu.itba.it.paw.web.restaurant.register;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.ListMultipleChoice;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.TextArea;
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
import ar.edu.itba.it.paw.domain.restaurant.Restaurant;
import ar.edu.itba.it.paw.domain.restaurant.RestaurantRepositoryType;
import ar.edu.itba.it.paw.domain.users.User;
import ar.edu.itba.it.paw.services.TimeService;
import ar.edu.itba.it.paw.web.BaseSession;
import ar.edu.itba.it.paw.web.base.BasePage;

@SuppressWarnings("serial")
public class RegisterRestaurantPage extends BasePage {
	
	@SpringBean private RestaurantRepositoryType restaurants;
	@SpringBean private AddressRepositoryType addresses;
	
	//Basic information
	private String name;
	private String description;
	private List<String> foodTypes;
	
	//Address
	private String street;
	private Integer number;
	private Integer floor;
	private String apartment;
	private Neighborhood neighborhood;
	private String province;
	private String city;
	
	//Time
	private Integer openTimeHour;
	private Integer openTimeMinutes;
	private Integer closeTimeHour;
	private Integer closeTimeMinutes;
	private Float minAmount;
	
	//Delivery
	private Integer deliveryOpenHour;
	private Integer deliveryOpenMinutes;
	private Integer deliveryCloseHour;
	private Integer deliveryCloseMinutes;
	private List<Neighborhood> neighborhoodDelivered;
	private Double deliveryCost;
	
	private static final List<String> TYPES_OF_FOOD = Arrays.asList(new String[] {
			"Árabe", "Argentina", "Armenia", "Autor", "China", "Deli", "Italiana", "Japonesa", "Mexicana", "Norteamericana", "Parrilla", "Peruana", "Vegetariana" });

	
	public RegisterRestaurantPage() {
		
		if(loggedUser == null || !loggedUser.getIsAdmin()){
			setResponsePage(getApplication().getHomePage());
		}
		
		Form<RegisterRestaurantPage> form = new Form<RegisterRestaurantPage>("registerRestaurantForm", new CompoundPropertyModel<RegisterRestaurantPage>(this)) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit() {
				Address address = new Address(street, number, floor, apartment, neighborhood, province, city);
				String openTimeString = TimeService.getTimeString(openTimeHour, openTimeMinutes);
				String closerTimeString = TimeService.getTimeString(closeTimeHour, closeTimeMinutes);
				String deliveryOpenString = TimeService.getTimeString(deliveryOpenHour, deliveryOpenMinutes);
				String deliveryCloseString = TimeService.getTimeString(deliveryCloseHour, deliveryCloseMinutes);
				Set<Neighborhood> deliveryNeighboardhood = new HashSet<Neighborhood>(neighborhoodDelivered);
				Restaurant rest = new Restaurant(-1, name, minAmount, openTimeString, closerTimeString, address, foodTypes, deliveryCost, deliveryOpenString, deliveryCloseString, deliveryNeighboardhood);
				rest.setDescription(description);
				if (restaurants.setRestaurant(rest)) {
					if (!continueToOriginalDestination()) { 
						setResponsePage(getApplication().getHomePage());
					}
				} else {
					error(getString("registerRestaurant.creationError"));
				}
			}
		};
		
		// Basic Information
		form.add(new TextField<String>("name").add(new MaximumLengthValidator(Restaurant.NAME_MAX_LENGTH)).setRequired(true));
		form.add(new TextArea<String>("description").add(new MaximumLengthValidator(Restaurant.DESCRIPTIO_MAX_LENGTH)).setRequired(true));
		
		IModel<List<String>> foodTypesModel = new LoadableDetachableModel<List<String>>() {
			@Override
			protected List<String> load() {
				return TYPES_OF_FOOD;
			}
		};
		form.add(new ListMultipleChoice<String>("foodTypes", foodTypesModel)
				.setMaxRows(4)
				.setRequired(true));
				
		form.add(new NumberTextField<Integer>("openTimeHour")
				.add(new RangeValidator<Integer>(0, 23))
				.setRequired(true));
		form.add(new NumberTextField<Integer>("openTimeMinutes")
				.add(new RangeValidator<Integer>(0, 59))
				.setRequired(true));
		form.add(new NumberTextField<Integer>("closeTimeHour")
				.add(new RangeValidator<Integer>(0, 23))
				.setRequired(true));
		form.add(new NumberTextField<Integer>("closeTimeMinutes")
				.add(new RangeValidator<Integer>(0, 59))
				.setRequired(true));
		form.add(new NumberTextField<Double>("minAmount")
				.setRequired(true));
		
		// Address 
		// TODO: update validations
		form.add(new TextField<String>("street").add(new MaximumLengthValidator(User.FIRST_NAME_MAX_SIZE)).setRequired(true));
		form.add(new TextField<Integer>("number"));
		form.add(new TextField<Integer>("floor"));
		form.add(new TextField<String>("apartment").add(new MaximumLengthValidator(User.FIRST_NAME_MAX_SIZE)));
		
		IModel<List<Neighborhood>> neighborhoodsModel = new LoadableDetachableModel<List<Neighborhood>>() {

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
		
		
		//Delivery
		form.add(new ListMultipleChoice<Neighborhood>("neighborhoodDelivered", neighborhoodsModel)
				.setMaxRows(2)
				.setChoiceRenderer(new ChoiceRenderer<Neighborhood>("name", "id"))
				.setRequired(true));	
		
		form.add(new NumberTextField<Integer>("deliveryOpenHour")
				.add(new RangeValidator<Integer>(0, 23))
				.setRequired(true));
		form.add(new NumberTextField<Integer>("deliveryOpenMinutes")
				.add(new RangeValidator<Integer>(0, 59))
				.setRequired(true));
		form.add(new NumberTextField<Integer>("deliveryCloseHour")
				.add(new RangeValidator<Integer>(0, 23))
				.setRequired(true));
		form.add(new NumberTextField<Integer>("deliveryCloseMinutes")
				.add(new RangeValidator<Integer>(0, 59))
				.setRequired(true));
		form.add(new NumberTextField<Double>("deliveryCost")
				.setRequired(true));
		
		form.add(new Button("create", new ResourceModel("registerRestaurant.create.title")));
		form.add(new FeedbackPanel("feedback"));
		add(form);
	}
}
