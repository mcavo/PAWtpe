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

import ar.edu.itba.it.paw.domain.restaurant.Restaurant;
import ar.edu.itba.it.paw.domain.restaurant.RestaurantRepository;
import ar.edu.itba.it.paw.web.base.BasePage;

public class HomePage extends BasePage {

	private static final long serialVersionUID = 1L;

//	@SpringBean
//	private RestaurantRepository restaurantRepo;

	public HomePage() {

//		IModel<User> userModel = new EntityModel<User>(User.class, getUser());
//		setDefaultModel(userModel);
//
		final IModel<List<Restaurant>> restsModel = new LoadableDetachableModel<List<Restaurant>>() {
			private static final long serialVersionUID = 1L;

			@Override
			protected List<Restaurant> load() {
//				User user = (User) getDefaultModelObject();
//
//				if (user == null) {
//					return hotels.getAllAvailableHotels();
//				} else {
//					return user.getFavourites();
//				}
				//return restaurantRepo.getAll();
				return null;
			}
		};
//
//		Label emptyListLabel = new Label("emptyListLabel",
//				"Todavía no tenés hoteles favoritos!");
//		if (userModel.getObject() != null) {
//			emptyListLabel.setVisible(((User) userModel.getObject())
//					.getFavourites().isEmpty());
//		} else {
//			emptyListLabel.setVisible(false);
//		}
//
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
//						Hotel h = getModelObject();
//						setResponsePage(new HotelPage(h));
					}

				};
				item.add(restLink);
//				hotelLink.add(new HotelTitlePanel("namePanel", item.getModelObject()));
//				hotelLink.add(new Label("address"));
			}

		};
		//add(emptyListLabel);
		add(listview);
	}

}
