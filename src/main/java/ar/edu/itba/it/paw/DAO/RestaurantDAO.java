package ar.edu.itba.it.paw.DAO;

import java.util.List;

import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.models.Restaurant;

public interface RestaurantDAO {

	public List<Restaurant> filterBy(String typeOfFood );
	//protected Restaurant getById(int id);
	//public List<Restaurant> getLastWeekAdded();
	public List<Restaurant> getAll();
	
}
