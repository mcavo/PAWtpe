package ar.edu.itba.it.paw.web;
import java.io.InputStream;
import java.util.Properties;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.resource.ContextRelativeResource;

import ar.edu.itba.it.paw.domain.restaurant.Restaurant;


@SuppressWarnings("serial")
public class RestaurantTitlePanel extends Panel{

	public RestaurantTitlePanel(String id, Restaurant rest) {
		super(id);
		add(new Label("name", rest.getName()));
//		InputStream is = getClass().getResourceAsStream(
//				"./HotelTitlePanel.properties");
//		Properties p = new Properties();
//		try {
//			p.load(is);
//			String starredHotel = p.getProperty("starredHotel");
//			this.starredHotel = Integer.parseInt(starredHotel);
//		} catch (Exception e) {
//			this.starredHotel = 1;
//		}
//
//		Label star = new Label("star", myStar);
//		star.setVisible(hotel.getComments().size() >= starredHotel ? true : false);
//		add(new Image("starIcon", new ContextRelativeResource("/img/star.png")));
//		add(star);
//		add(new Label("name", hotel.getName()));
//		
//		Label delete = new Label("deletedHotel", "Hotel borrado");
//		if(hotel.isDeleted()){
//			delete.setVisible(true);
//		}else{
//			delete.setVisible(false);
//		}
//		add(delete);
	}

}
