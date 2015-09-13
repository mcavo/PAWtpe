package ar.edu.itba.it.paw.services;

import java.util.List;

import ar.edu.itba.it.paw.DAO.RestaurantDAO;
import ar.edu.itba.it.paw.models.Restaurant;
import ar.edu.itba.it.paw.models.User;

public class RestService {

	public static List<Restaurant> getLastWeekRestaurants(){
		return RestaurantDAO.getInstance().getLastWeekAdded();
	}

	public static Restaurant getRestaurant(String name, String address) {
		String key = name.concat(address);
		return RestaurantDAO.getInstance().getRestaurant(key);
	}
	
	public static boolean canQualify(Restaurant r, User usr){
		System.out.println(r);
		System.out.println(usr);
		return !r.getQualifications().keySet().contains(usr.getId());
	}
}
