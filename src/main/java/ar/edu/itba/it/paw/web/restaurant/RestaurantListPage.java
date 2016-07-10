package ar.edu.itba.it.paw.web.restaurant;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.extensions.markup.html.form.select.Select;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.ListMultipleChoice;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.domain.address.Neighborhood;
import ar.edu.itba.it.paw.domain.restaurant.Restaurant;
import ar.edu.itba.it.paw.domain.restaurant.RestaurantRepositoryType;
import ar.edu.itba.it.paw.web.base.BasePage;
import ar.edu.itba.it.paw.web.restaurant.register.RegisterRestaurantPage;

public class RestaurantListPage extends BasePage{
	private static final long serialVersionUID = 1L;
	private static final List<String> TYPES_OF_FOOD = Arrays.asList(new String[] {
			"Arabe", "Argentina", "Armenia", "Autor", "China", "Deli", "Italiana", "Japonesa", "Mexicana", "Norteamericana", "Parrilla", "Peruana", "Vegetariana" });
	private String foodTypes;
	@SpringBean
	private RestaurantRepositoryType restaurantRepo;
	
	public RestaurantListPage() {
		initialize(restaurantRepo.getAll());
	}

	public RestaurantListPage(String foodType) {
		initialize(restaurantRepo.filterBy(foodType.toLowerCase()));
	}
	
	private void initialize(List<Restaurant> rests) {
		Form<RestaurantListPage> form = new Form<RestaurantListPage>("filterRestaurantForm", new CompoundPropertyModel<RestaurantListPage>(this)) {
			@Override
			protected void onSubmit() {
				setResponsePage(new RestaurantListPage(foodTypes));
			}
		};
		IModel<List<String>> foodTypesModel = new LoadableDetachableModel<List<String>>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected List<String> load() {
				return TYPES_OF_FOOD;
			}
		};
		form.add(new DropDownChoice<String>("foodTypes", foodTypesModel));
		add(form);
		
		final IModel<List<Restaurant>> restsModel = new LoadableDetachableModel<List<Restaurant>>() {
			private static final long serialVersionUID = 1L;

			@Override
			protected List<Restaurant> load() {
				return rests;
			}
		};

		ListView<Restaurant> listview = new PropertyListView<Restaurant>("rests",
				restsModel) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<Restaurant> item) {

				Link<Restaurant> restLink = new Link<Restaurant>("viewRest",
						item.getModel()) {

					private static final long serialVersionUID = 1L;

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
}
