package ar.edu.itba.it.paw.services;

import java.util.List;

import ar.edu.itba.it.paw.models.Calification;
import ar.edu.itba.it.paw.models.Restaurant;

public interface RestaurantService {

	public List<Restaurant> getAllRestaurants();
	
	public List<Restaurant> getRestaurants(String typeOfFood);
	
	public Restaurant getRestaurant(String name, String street, String number, String neighborhood, String city, String province, String floor, String apartment);
	
	public void addCalification(int usrId, Restaurant rest, Calification q);
	
	public void setRestaurant(String name , String description , String[] types , String timeFrom , String timeTo , String street , String number , String city , String province , String floor , String apartment , String neighborhood, String minimum, String cost) throws Exception;
	
	public boolean validateId(String restid);

	public List<Restaurant> getLastWeekRestaurants();

	public List<Restaurant> getPopularRestaurants();
}
