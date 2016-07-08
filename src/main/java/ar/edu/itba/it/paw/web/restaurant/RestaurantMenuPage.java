package ar.edu.itba.it.paw.web.restaurant;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.domain.restaurant.RestaurantRepositoryType;
import ar.edu.itba.it.paw.domain.restaurant.Restaurant;
import ar.edu.itba.it.paw.domain.users.User;
import ar.edu.itba.it.paw.web.BaseSession;
import ar.edu.itba.it.paw.web.base.BasePage;

@SuppressWarnings("serial")
public class RestaurantMenuPage extends BasePage{

	@SpringBean
	RestaurantRepositoryType rests;
	
	public RestaurantMenuPage(Restaurant r){
		add(new Label("name", r.getName()));
		add(new Label("score", String.valueOf(r.getScore())));
		add(new Label("count", String.valueOf(r.getCountComments())));
		
		Link menuLink = new Link("menuLink"){
			
			@Override
			public void onClick() {

			}
			
		};
		add(menuLink.setVisible(true));
		
		Link infoLink = new Link("infoLink"){

			@Override
			public void onClick() {
				setResponsePage(new RestaurantPage(r));
			}
			
		};
		add(infoLink.setVisible(true));
		
		add(new Label("sprice", String.valueOf(r.getDelamount())));
		
		double tprice = 0;
		add(new Label("tprice", String.valueOf(tprice)));
		
		double subprice = 0;
		add(new Label("subprice", String.valueOf(subprice)));
		
		User user = BaseSession.get().getUser();
		Label lblCantOrder = new Label("lblCantOrder", getString("can_not_order"));
		boolean ok2order = false;
		if(user != null){
			ok2order = rests.userCanOrder(user, r);
		}
		lblCantOrder.setVisible(ok2order);
		add(lblCantOrder);
	}
}
