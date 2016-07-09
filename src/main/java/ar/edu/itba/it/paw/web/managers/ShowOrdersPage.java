package ar.edu.itba.it.paw.web.managers;

import java.util.LinkedList;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.domain.restaurant.Order;
import ar.edu.itba.it.paw.domain.restaurant.OrderRepositoryType;
import ar.edu.itba.it.paw.domain.restaurant.Restaurant;
import ar.edu.itba.it.paw.exceptions.LoadOrderException;
import ar.edu.itba.it.paw.web.base.BasePage;

public class ShowOrdersPage extends BasePage{

	private static final long serialVersionUID = 1L;

	@SpringBean
	private OrderRepositoryType orderRepo;
	
	public ShowOrdersPage(Restaurant rest) {
		
		add(new Label("restName", rest.getName()));
		
		final IModel<List<Order>> ordersModel = new LoadableDetachableModel<List<Order>>() {
			private static final long serialVersionUID = 1L;

			@Override
			protected List<Order> load() {
				try {
					return orderRepo.getHistory(rest);
				} catch (LoadOrderException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return new LinkedList<Order>();
				}
			}
		};
		
		ListView<Order> listview = new PropertyListView<Order>("orders", ordersModel) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<Order> item) {
				item.add(new OrderPanel("orderPanel", item.getModelObject()));
			}

		};
		add(listview);
	}
}
