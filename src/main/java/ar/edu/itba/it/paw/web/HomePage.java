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

import ar.edu.itba.it.paw.web.base.BasePage;

public class HomePage extends BasePage {

	private static final long serialVersionUID = 1L;

//	@SpringBean
//	private HotelRepo hotels;

	public HomePage() {

//		IModel<User> userModel = new EntityModel<User>(User.class, getUser());
//		setDefaultModel(userModel);
//
//		final IModel<List<Hotel>> hotelsModel = new LoadableDetachableModel<List<Hotel>>() {
//			private static final long serialVersionUID = 1L;
//
//			@Override
//			protected List<Hotel> load() {
//				User user = (User) getDefaultModelObject();
//
//				if (user == null) {
//					return hotels.getAllAvailableHotels();
//				} else {
//					return user.getFavourites();
//				}
//			}
//		};
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
//		ListView<Hotel> listview = new PropertyListView<Hotel>("hotels",
//				hotelsModel) {
//			private static final long serialVersionUID = 1L;
//
//			@Override
//			protected void populateItem(ListItem<Hotel> item) {
//
//				Link<Hotel> hotelLink = new Link<Hotel>("viewHotel",
//						item.getModel()) {
//
//					private static final long serialVersionUID = 1L;
//
//					@Override
//					public void onClick() {
//						Hotel h = getModelObject();
//						setResponsePage(new HotelPage(h));
//					}
//
//				};
//				item.add(hotelLink);
//				hotelLink.add(new HotelTitlePanel("namePanel", item.getModelObject()));
//				hotelLink.add(new Label("address"));
//			}
//
//		};
//		add(emptyListLabel);
//		add(listview);
	}

}
