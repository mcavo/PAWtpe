package ar.edu.itba.it.paw.web;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.domain.restaurant.Restaurant;
import ar.edu.itba.it.paw.domain.restaurant.RestaurantRepositoryType;
import ar.edu.itba.it.paw.domain.users.User;
import ar.edu.itba.it.paw.domain.users.UserRepositoryType;
import ar.edu.itba.it.paw.services.DateService;
import ar.edu.itba.it.paw.web.base.BasePage;
import ar.edu.itba.it.paw.web.restaurant.RestaurantPage;
import ar.edu.itba.it.paw.web.restaurant.RestaurantPanel;

@SuppressWarnings("serial")
public class HomePage extends BasePage {

	@SpringBean
	private RestaurantRepositoryType restaurantRepo;
	
	@SpringBean
	private UserRepositoryType userRepo;

	public HomePage() {
		addLastWeekRestaurnts();
		addNewRestaurantsFromLastSession();
	}
	
	private void addLastWeekRestaurnts() {
		final IModel<List<Restaurant>> restsModel = new LoadableDetachableModel<List<Restaurant>>() {

			@Override
			protected List<Restaurant> load() {
				return restaurantRepo.getLastWeekAdded();
			}
		};

		ListView<Restaurant> listview = new PropertyListView<Restaurant>("rests",
				restsModel) {

			@Override
			protected void populateItem(ListItem<Restaurant> item) {

				Link<Restaurant> restLink = new Link<Restaurant>("viewRest",
						item.getModel()) {

					@Override
					public void onClick() {
						Restaurant r = getModelObject();
						setResponsePage(new RestaurantPage(r));
					}

				};
				
				restLink.add(new RestaurantPanel("restPanel", item.getModelObject()));
				item.add(restLink);
			}

		};
		add(listview);
	}
	
	private void addNewRestaurantsFromLastSession() {
		
		add(new Label("newRestaurants",new ResourceModel("newRestaurants"))
				.setVisible(loggedUser != null));
		
		final IModel<List<Restaurant>> restsModel = new LoadableDetachableModel<List<Restaurant>>() {

			@Override
			protected List<Restaurant> load() {
				if (loggedUser == null) {
					return new ArrayList<Restaurant>();
				}
				User user = users.getUserById(loggedUser.getId());
				return restaurantRepo.getNewRestaurantsFromLastSession(user);
			}
		};

		ListView<Restaurant> listview = new PropertyListView<Restaurant>("newRest",
				restsModel) {

			@Override
			protected void populateItem(ListItem<Restaurant> item) {

				Link<Restaurant> restLink = new Link<Restaurant>("viewNewRest",
						item.getModel()) {

					@Override
					public void onClick() {
						Restaurant r = getModelObject();
						setResponsePage(new RestaurantPage(r));
					}

				};
				
				restLink.add(new RestaurantPanel("viewRestPanel", item.getModelObject()));
				item.add(restLink);
			}

		};
		listview.setVisible(loggedUser != null);
		add(listview);
	}
}
