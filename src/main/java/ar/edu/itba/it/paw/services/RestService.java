package ar.edu.itba.it.paw.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import ar.edu.itba.it.paw.DAO.RestaurantDAO;
import ar.edu.itba.it.paw.models.Address;
import ar.edu.itba.it.paw.models.Calification;
import ar.edu.itba.it.paw.models.Restaurant;

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
	
	public static void setRestaurant(String name , String description , String[] types , String timeFrom , String timeTo , String street , String number , String city , String province , String floor , String apartment , String neighborhood, String minimum, String cost) throws Exception {
		ArrayList<String> validTypes;
		int floorV = -1;
		int numberV; 
		float costV;
		double minimumPurchase;
		float from;
		float to;
		try {
			from = Float.valueOf(timeFrom.replace(':', '.'));
			to = Float.valueOf(timeTo.replace(':', '.'));
			if (!floor.isEmpty()) {
				floorV = Integer.valueOf(floor);
				ValidateDataService.validateFloor(floorV);
			}
			numberV = Integer.valueOf(number);
			ValidateDataService.validateStringLength(name, 30);
			if (description != null && !description.isEmpty()) {
				ValidateDataService.validateStringLength(description, 500);	
			}
			ValidateDataService.validateInterval(from, to);
			ValidateDataService.validateStringLength(street, 30);
			ValidateDataService.validateStringLength(city, 30);
			ValidateDataService.validateStringLength(province, 30);
			ValidateDataService.validateStringLength(neighborhood, 40);
			if (apartment != null && !apartment.isEmpty()) {
				ValidateDataService.validateApartment(apartment);	
			}
			validTypes = ValidateDataService.validateTypes(types);
			costV = ValidateDataService.validateCost(cost);
			minimumPurchase = ValidateDataService.validateMinimum(minimum);
			
		} catch (Exception e) {
			throw new Exception("Invalid parameters");
		}
		Address address = new Address(street, numberV, floorV, apartment, neighborhood, city, province);
		Restaurant rest = new Restaurant(-1, name, minimumPurchase, from, to, address, validTypes, null, costV);
		
		RestaurantDAO.getInstance().setRestaurant(rest);
		
	}
	
}
