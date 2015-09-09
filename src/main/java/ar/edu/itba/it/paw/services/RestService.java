package ar.edu.itba.it.paw.services;

import java.util.List;

import ar.edu.itba.it.paw.DAO.RestaurantDAO;
import ar.edu.itba.it.paw.models.Restaurant;

public class RestService {

	public static List<Restaurant> getLastWeekRestaurants(){
		return RestaurantDAO.getInstance().getLastWeekAdded();
	}

	public static Restaurant getRestaurant(String name) {
		return RestaurantDAO.getInstance().getRestaurant(name);
	}
}
