package ar.edu.itba.it.paw.web.restaurant;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.domain.restaurant.Order;
import ar.edu.itba.it.paw.domain.restaurant.OrderRepositoryType;
import ar.edu.itba.it.paw.domain.restaurant.Restaurant;
import ar.edu.itba.it.paw.domain.users.User;
import ar.edu.itba.it.paw.exceptions.LoadOrderException;
import ar.edu.itba.it.paw.web.BaseSession;
import ar.edu.itba.it.paw.web.base.BasePage;

@SuppressWarnings("serial")
public class RestaurantPage extends BasePage{

	@SpringBean
	OrderRepositoryType orders;
	
	public RestaurantPage(Restaurant r){
		add(new Label("nameText", r.getName()));
		User user = BaseSession.get().getUser();
		List<Order> order_list = new ArrayList<Order>();
		try {
			order_list = orders.getHistory(r);
		} catch (LoadOrderException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} 
		int orderId = -1;
		for (Order order : order_list) {
			if(order.getUser().equals(user) && order.getStatus() == 0){
				orderId = order.getId();
			}
		}
		Label lbl_order = new Label("orderText", "Su numero de pedido es: "+ orderId );
		add(lbl_order.setVisible(orderId != -1));
		
		add(new Label("score", String.valueOf(r.getScore())));
		add(new Label("count", String.valueOf(r.getCountComments())));
		
		Link menuLink = new Link("menuLink"){
				
			@Override
			public void onClick() {
				setResponsePage(new RestaurantMenuPage(r));
			}
			
		};
		add(menuLink.setVisible(true));
		
		Link infoLink = new Link("infoLink"){

			@Override
			public void onClick() {
				
			}
			
		};
		add(infoLink.setVisible(true));
		
		add(new Label("descriptionText", r.getDescription()));
	}
}
