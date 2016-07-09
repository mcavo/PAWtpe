package ar.edu.itba.it.paw.web.managers;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;

import ar.edu.itba.it.paw.domain.address.Address;
import ar.edu.itba.it.paw.domain.restaurant.Order;
import ar.edu.itba.it.paw.domain.users.User;


@SuppressWarnings("serial")
public class OrderPanel extends Panel {
	
	public OrderPanel(String id, Order order) {
		super(id);
		User user = order.getUser();
		add(new Label("userName", new StringResourceModel("user_name", this, new Model<User>(user))));
		Address address = user.getAddress();
		add(new Label("userAddress", new StringResourceModel("address", this, new Model<Address>(address))));
		
		add(new Label("total", new StringResourceModel("total", this, new Model<Order>(order))));
	}

}
