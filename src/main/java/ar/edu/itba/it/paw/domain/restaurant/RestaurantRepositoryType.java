package ar.edu.itba.it.paw.domain.restaurant;

import java.util.List;

import ar.edu.itba.it.paw.domain.users.User;

public interface RestaurantRepositoryType {

	public List<Restaurant> getMostPopular();
	public List<Restaurant> filterBy(String typeOfFood );
	public Restaurant getById(int id);
	public boolean setRestaurant( Restaurant rest);
	public List<Restaurant> getAll();
	public int saveRestaurant(Restaurant rest);
	public boolean validateId(int id);
	public boolean userCanOrder(User user, Restaurant rest);
	public List<Restaurant> getLastWeekAdded();
	public List<Restaurant> getNewRestaurantsFromLastSession(User user);
}
