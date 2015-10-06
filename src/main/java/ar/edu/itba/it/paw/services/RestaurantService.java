package ar.edu.itba.it.paw.services;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.itba.it.paw.models.Restaurant;

public interface RestaurantService {

	public List<Restaurant> getAllRestaurants();
	
	public List<Restaurant> getRestaurants(String typeOfFood);
}
