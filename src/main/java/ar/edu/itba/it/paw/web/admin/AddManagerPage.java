package ar.edu.itba.it.paw.web.admin;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.domain.restaurant.Restaurant;
import ar.edu.itba.it.paw.domain.restaurant.RestaurantRepositoryType;
import ar.edu.itba.it.paw.domain.users.Credential;
import ar.edu.itba.it.paw.domain.users.ManagerRepositoryType;
import ar.edu.itba.it.paw.domain.users.User;
import ar.edu.itba.it.paw.exceptions.NoManagersAvailableException;
import ar.edu.itba.it.paw.web.base.BasePage;

@SuppressWarnings("serial")
public class AddManagerPage extends BasePage {

	@SpringBean private RestaurantRepositoryType restaurants;
	@SpringBean private ManagerRepositoryType managers;
	
	private Restaurant restaurant;
	private Credential manager;
	
	public AddManagerPage() {
		
		if (!loggedUser.getIsAdmin()) {
			setResponsePage(getApplication().getHomePage());
		}
		
		Form<AddManagerPage> form = new Form<AddManagerPage>("addManagerForm", new CompoundPropertyModel<AddManagerPage>(this)) {

			@Override
			protected void onSubmit() {
				Restaurant newRestaurant = restaurants.getById(restaurant.getId());
				User newManager = users.getUser(manager); 
						
				newManager.setManager(true);
				managers.setManager(newManager);
				newRestaurant.addManager(newManager);
				setResponsePage(getApplication().getHomePage());
			}
		};
		
		IModel<List<Restaurant>> restaurantsModel = new LoadableDetachableModel<List<Restaurant>>() {

			@Override
			protected List<Restaurant> load() {
				return restaurants.getAll();
			}
		};
		form.add(new DropDownChoice<Restaurant>("restaurant", restaurantsModel)
				.setChoiceRenderer(new ChoiceRenderer<Restaurant>("string", "id"))
				.setRequired(true));	
		
		IModel<List<Credential>> usersModel = new LoadableDetachableModel<List<Credential>>() {

			@Override
			protected List<Credential> load() {
				try {
					return managers.getManagersAvailables();
				} catch (NoManagersAvailableException e) {
					return new ArrayList<Credential>();
				}
			}
		};
		form.add(new DropDownChoice<Credential>("manager", usersModel)
				.setChoiceRenderer(new ChoiceRenderer<Credential>("mail", "id"))
				.setRequired(true));
		
		form.add(new Button("assign", new ResourceModel("addManager.submit.label")));
		form.add(new FeedbackPanel("feedback"));
		add(form);
	}
}
