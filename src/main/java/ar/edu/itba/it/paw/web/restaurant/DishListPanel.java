package ar.edu.itba.it.paw.web.restaurant;

import java.util.LinkedList;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;

import ar.edu.itba.it.paw.domain.restaurant.Dish;

public class DishListPanel extends Panel {

	private static final long serialVersionUID = 1L;
	
	private List<DishPanel> dishPanels;

	public DishListPanel(String id, List<Dish> dishList) {
		super(id);
		dishPanels = new LinkedList<>();
		add(createDishList("dishList", dishList));
		add(new Label("name", dishList.get(0).getSection()));
	}
	
	private ListView<Dish> createDishList(String id, List<Dish> dishes) {
		return new ListView<Dish>(id, dishes) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(final ListItem<Dish> item) {
				DishPanel dishPanel = new DishPanel("dishPanel", item);
				dishPanels.add(dishPanel);
				item.add(dishPanel);
			}
		};
	}
	
	public List<DishPanel> getDishPanel() {
		return dishPanels;
	}

}
