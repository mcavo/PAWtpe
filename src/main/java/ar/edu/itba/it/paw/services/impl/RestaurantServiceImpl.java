package ar.edu.itba.it.paw.services.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.itba.it.paw.DAO.RestaurantDAO;
import ar.edu.itba.it.paw.DAO.impl.RestaurantDAOImpl;
import ar.edu.itba.it.paw.models.Address;
import ar.edu.itba.it.paw.models.Calification;
import ar.edu.itba.it.paw.models.Restaurant;
import ar.edu.itba.it.paw.services.CalificationService;
import ar.edu.itba.it.paw.services.RestaurantService;
import ar.edu.itba.it.paw.services.ValidateDataService;

@Service
public class RestaurantServiceImpl implements RestaurantService{

	private RestaurantDAO restaurantDAO;
	
	public RestaurantServiceImpl(){
		
	}
	
	public RestaurantServiceImpl(RestaurantDAO dao){
		this.restaurantDAO = dao;
	}
	
	public List<Restaurant> getAllRestaurants() {
		List<Restaurant> res = restaurantDAO.getAll();
		if(res!=null)
			res.sort(new Comparator<Restaurant> () {

				public int compare(Restaurant o1, Restaurant o2) {
					return o1.getName().compareTo(o2.getName());
				}
			
			});
		return res;
	}
	
	public List<Restaurant> getRestaurants(String typeOfFood) {
		List<Restaurant> res = restaurantDAO.filterBy(typeOfFood);
		if(res!=null)
			res.sort(new Comparator<Restaurant> () {

				public int compare(Restaurant o1, Restaurant o2) {
					return o1.getName().compareTo(o2.getName());
				}
			
			});
		return res;
	}

	public Restaurant getRestaurant(String name, String street, String number, String neighborhood, String city, String province, String floor, String apartment) {
		if(name == null){
			return null;
		}	
		//return restaurantDAO.getRestaurant(name, street, Integer.valueOf(number), neighborhood, city, province, Integer.valueOf(floor), apartment);
		return restaurantDAO.getRestaurant(name, new Address(street, Integer.valueOf(number), Integer.valueOf(floor), apartment, neighborhood, city, province));
	}

	public void addCalification(int usrId, Restaurant rest, Calification q) {
		rest.getQualifications().put(usrId, q);
	}
	
	public void setRestaurant(String name , String description , String[] types , String timeFrom , String timeTo , String street , String number , String city , String province , String floor , String apartment , String neighborhood, String minimum, String cost) throws Exception {
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
		
		restaurantDAO.setRestaurant(rest);
		
	}

	public boolean validateId(String restid) {
		int id;
		try {
			id = Integer.parseInt(restid);
			return restaurantDAO.validateId(id);
		} catch (java.lang.NumberFormatException e) {
			return false;
		}
	}

	@Override
	public List<Restaurant> getLastWeekRestaurants() {
		return (new RestaurantDAOImpl()).getLastWeekAdded();
	}

}
