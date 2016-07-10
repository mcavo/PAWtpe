package ar.edu.itba.it.paw.web.managers.dishes;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.StringValidator.MaximumLengthValidator;

import ar.edu.itba.it.paw.domain.restaurant.Restaurant;
import ar.edu.itba.it.paw.domain.users.ManagerRepositoryType;
import ar.edu.itba.it.paw.exceptions.DuplicateDishException;
import ar.edu.itba.it.paw.exceptions.InvalidPriceException;
import ar.edu.itba.it.paw.exceptions.InvalidSectionName;
import ar.edu.itba.it.paw.exceptions.NoRestaurantException;
import ar.edu.itba.it.paw.web.base.BasePage;

@SuppressWarnings("serial")
public class AddDishPage extends BasePage {
	
	@SpringBean private ManagerRepositoryType managers;
	
	private Restaurant restaurant;
	
	private String section;
	private String dish;
	private String description;
	private int price;
	
	public AddDishPage() {
		
		if (loggedUser == null || !loggedUser.getIsManager()) {
			setResponsePage(getApplication().getHomePage());
		}
		
		initialize();
		
		Form<AddDishPage> form = new Form<AddDishPage>("addDishForm", new CompoundPropertyModel<AddDishPage>(this)) {

			@Override
			protected void onSubmit() {
				
				try {
					Restaurant rest = managers.getRestaurant(loggedUser);
					managers.addDish(rest, section, dish, Integer.valueOf(price), description);	
					if (!continueToOriginalDestination()) { 
						setResponsePage(getApplication().getHomePage());
					}
				}catch (NoRestaurantException e) {
					error(getString("addDish.noRestaurantError") + loggedUser.getFirstName());
				} catch (InvalidPriceException e) {
					error(getString("addDish.invalidPriceError"));
				} catch (InvalidSectionName e) {
					error(getString("addDish.invalidSectionError"));
				} catch (DuplicateDishException e) {
					error(getString("addDish.duplicatedDishError"));
				}
			}
		};
		
		add(new Label("title", new StringResourceModel("addDish.title.label", this, new Model<Restaurant>(restaurant))));
		form.add(new TextField<String>("section").add(new MaximumLengthValidator(Restaurant.NAME_MAX_LENGTH)).setRequired(true));
		form.add(new TextField<String>("dish").add(new MaximumLengthValidator(Restaurant.NAME_MAX_LENGTH)).setRequired(true));
		form.add(new TextArea<String>("description").add(new MaximumLengthValidator(Restaurant.DESCRIPTIO_MAX_LENGTH)).setRequired(true));
		form.add(new NumberTextField<Integer>("price").setRequired(true));
		
		form.add(new Button("add", new ResourceModel("addDish.add.title")));
		form.add(new FeedbackPanel("feedback"));
		add(form);
	}
	
	private void initialize() {
		try {
			restaurant = managers.getRestaurant(loggedUser);
		} catch (NoRestaurantException e) {
			setResponsePage(getApplication().getHomePage());
		}
	}
}
