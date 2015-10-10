package ar.edu.itba.it.paw.DAO;

import java.util.List;

import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.models.Address;
import ar.edu.itba.it.paw.models.Menu;
import ar.edu.itba.it.paw.models.Restaurant;

public interface RestaurantDAO {

	public List<Restaurant> filterBy(String typeOfFood );
	
	public Restaurant getById(int id);
	
	public List<Restaurant> getLastWeekAdded();
	
	public List<Restaurant> getAll();
	
	public List<String> getTypesOfFoodByRestId(int restId);
	
	public Menu getMenuByRestId(int restId);
	
	public Restaurant getRestaurant(String name, Address address);//String street, int number, String neighborhood, String city, String province, int floor, String apartment);
	
	public Restaurant matchRestAddress(String name, int addressId);
	
	public void setRestaurant(Restaurant rest) throws Exception;
	
	public boolean validateId(int id);
	
	
}
