package ar.edu.itba.it.paw.services;

import java.util.List;

import ar.edu.itba.it.paw.DAO.RestaurantDAO;
import ar.edu.itba.it.paw.models.Restaurant;
import ar.edu.itba.it.paw.models.User;

public class RestService {

	public static List<Restaurant> getLastWeekRestaurants(){
		return RestaurantDAO.getInstance().getLastWeekAdded();
	}

	public static Restaurant getRestaurant(String name, String street, String number, String neighborhood, String city, String province, String floor, String apartment) {
		if(name == null){
			return null;
		}	

		return RestaurantDAO.getInstance().getRestaurant(name, street, number, neighborhood, city, province, floor, apartment);
	}
	
	public static boolean canQualify(Restaurant r, User usr){
		return !r.getQualifications().keySet().contains(usr.getId());
	}
}
