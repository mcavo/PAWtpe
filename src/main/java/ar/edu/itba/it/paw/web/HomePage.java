package ar.edu.itba.it.paw.web;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.domain.restaurant.RestRepo;
import ar.edu.itba.it.paw.domain.restaurant.Restaurant;
import ar.edu.itba.it.paw.web.base.BasePage;

public class HomePage extends BasePage {

	private static final long serialVersionUID = 1L;

	@SpringBean
	private RestRepo restaurantRepo;

	public HomePage() {
		
		final IModel<List<Restaurant>> restsModel = new LoadableDetachableModel<List<Restaurant>>() {
			private static final long serialVersionUID = 1L;

			@Override
			protected List<Restaurant> load() {
				return restaurantRepo.getAll();
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
				
				restLink.add(new RestaurantTitlePanel("namePanel", item.getModelObject()));
				//hotelLink.add(new Label("address"));
				item.add(restLink);
			}

		};
		add(listview);
	}
}
