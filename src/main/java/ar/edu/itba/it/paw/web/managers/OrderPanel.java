package ar.edu.itba.it.paw.web.managers;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.domain.address.Address;
import ar.edu.itba.it.paw.domain.address.AddressRepositoryType;
import ar.edu.itba.it.paw.domain.restaurant.Order;
import ar.edu.itba.it.paw.domain.restaurant.OrderRepositoryType;
import ar.edu.itba.it.paw.domain.users.User;


@SuppressWarnings("serial")
public class OrderPanel extends Panel{
	
	public OrderPanel(String id, Order order) {
		super(id);
		User user = order.getUser();
		add(new Label("userName", user.getFirstName() +" "+ user.getLastName()));
		Address address = user.getAddress();
		
		int floor = 0;
		if(address.getFloor() != null){
			floor = address.getFloor();
		}
		String user_address = address.getStreet();
		user_address.concat(" ").concat(String.valueOf(address.getNumber()));
		if(floor != 0){
			user_address.concat(" ").concat(String.valueOf(floor));
		}
		
		if (address.getApartment() != null) {
			user_address.concat(" ").concat(address.getApartment());
		}
		user_address.concat(" ").concat(address.getNeighborhood().getName());
		add(new Label("userAddress", user_address));
		
		add(new Label("total", String.valueOf(order.getTotal())));
	}

}
