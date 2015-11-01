package ar.edu.itba.it.paw.web.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.models.Restaurant;
import ar.edu.itba.it.paw.services.RestaurantService;

@Component
public class RestaurantConverter implements Converter<String, Restaurant>{
	
	private RestaurantService restaurants;
	
	@Autowired
	public RestaurantConverter(RestaurantService restaurant) {
		this.restaurants = restaurant;
	}
	
	public Restaurant convert(String arg0) {
		return restaurants.getRestaurant(Integer.valueOf(arg0)); 
	}
}
