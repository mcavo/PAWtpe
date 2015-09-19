package ar.edu.itba.it.paw.services;

import java.util.Comparator;
import java.util.List;

import ar.edu.itba.it.paw.DAO.RestaurantDAO;
import ar.edu.itba.it.paw.models.Calification;
import ar.edu.itba.it.paw.models.Restaurant;
import ar.edu.itba.it.paw.models.User;

public class RestService {

	public static List<Restaurant> getLastWeekRestaurants(){
		return RestaurantDAO.getInstance().getLastWeekAdded();
	}
	
	public static List<Restaurant> getAllRestaurants() {
		List<Restaurant> res = RestaurantDAO.getInstance().getAll();
		if(res!=null)
			res.sort(new Comparator<Restaurant> () {

				public int compare(Restaurant o1, Restaurant o2) {
					return o1.getName().compareTo(o2.getName());
				}
			
			});
		return res;
	}
	
	public static List<Restaurant> getRestaurants(String typeOfFood) {
		List<Restaurant> res = RestaurantDAO.getInstance().filterBy(typeOfFood);
		if(res!=null)
			res.sort(new Comparator<Restaurant> () {

				public int compare(Restaurant o1, Restaurant o2) {
					return o1.getName().compareTo(o2.getName());
				}
			
			});
		return res;
	}

	public static Restaurant getRestaurant(String name, String street, String number, String neighborhood, String city, String province, String floor, String apartment) {
		if(name == null){
			return null;
		}	
		return RestaurantDAO.getInstance().getRestaurant(name, street, Integer.valueOf(number), neighborhood, city, province, Integer.valueOf(floor), apartment);
	}

	public static void addCalification(int usrId, Restaurant rest, Calification q) {
		rest.getQualifications().put(usrId, q);
	}
	
}
