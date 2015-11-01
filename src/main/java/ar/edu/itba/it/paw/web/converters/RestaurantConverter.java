package ar.edu.itba.it.paw.web.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.models.Restaurant;
import ar.edu.itba.it.paw.repositories.RestaurantRepository;

@Component
public class RestaurantConverter implements Converter<String, Restaurant>{
	
	private RestaurantRepository restaurants;
	
	@Autowired
	public RestaurantConverter(RestaurantRepository restaurant) {
		this.restaurants = restaurant;
	}
	
	public Restaurant convert(String arg0) {
		return restaurants.getById(Integer.valueOf(arg0)); 
	}
}
