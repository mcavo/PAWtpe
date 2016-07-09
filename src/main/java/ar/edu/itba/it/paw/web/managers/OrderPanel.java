package ar.edu.itba.it.paw.web.managers;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

import ar.edu.itba.it.paw.domain.address.Address;
import ar.edu.itba.it.paw.domain.restaurant.Order;
import ar.edu.itba.it.paw.domain.users.User;


@SuppressWarnings("serial")
public class OrderPanel extends Panel{

	public OrderPanel(String id, Order order) {
		super(id);
		User user = order.getUser();
		add(new Label("userName", user.getFirstName() +" "+ user.getLastName()));
		Address address = user.getAddress();
		
		String user_address = address.getStreet() + " " + address.getNumber();
		if(address.getFloor() != 0){
			user_address += " "+ address.getFloor();
		}
		if (address.getApartment() != null) {
			user_address += " "+ address.getApartment();
		}
		user_address += ", "+ address.getNeighborhood().getName();
		add(new Label("userAddress", user_address));
		
		add(new Label("total", String.valueOf(order.getTotal())));
	}

}
