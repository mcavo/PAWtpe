package ar.edu.itba.it.paw.web;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

import ar.edu.itba.it.paw.domain.restaurant.Restaurant;


@SuppressWarnings("serial")
public class RestaurantPanel extends Panel{

	public RestaurantPanel(String id, Restaurant rest) {
		super(id);
		add(new Label("restName", rest.getName()));
		add(new Label("restAddress", rest.getAddress().toString()));
		add(new Label("restSchedule", getString("schedule") + " " + rest.getDeliveryfrom() + " -  " + rest.getDeliveryto()));
		
		String categories = "";
		for(String c : rest.getTypesOfFood()) {
			categories += "<span class='label label-primary'>" + c + "</span> ";
		}
		add(new Label("restCategories", categories).setEscapeModelStrings(false));
		
		String calification = "<span class='label label-warning'>" + rest.getScore() + "</span> " + rest.getCountComments() + " " + getString("califications");
		add(new Label("restCalification", calification).setEscapeModelStrings(false));
	}

}
