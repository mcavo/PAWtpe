package ar.edu.itba.it.paw.services.impl;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.itba.it.paw.DAO.impl.RestaurantDAO;
import ar.edu.itba.it.paw.models.Restaurant;
import ar.edu.itba.it.paw.services.RestaurantService;

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


}
