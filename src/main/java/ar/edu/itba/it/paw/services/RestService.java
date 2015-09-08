package ar.edu.itba.it.paw.services;

import java.util.List;

import ar.edu.itba.it.paw.RestaurantDAO;
import ar.edu.itba.it.paw.models.Restaurant;

public class RestService {

	public static List<Restaurant> getLastWeekRestaurants(){
		return RestaurantDAO.getInstance().getLastWeekAdded();
	}
}
