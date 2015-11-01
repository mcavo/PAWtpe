package ar.edu.itba.it.paw.controllers;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.models.Calification;
import ar.edu.itba.it.paw.models.Restaurant;
import ar.edu.itba.it.paw.repositories.RestaurantRepository;

@Controller
public class HomepageController {
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	public HomepageController(RestaurantRepository restaurantRepository) {
		this.restaurantRepository = restaurantRepository;
	}
	
	@RequestMapping(value="/homepage", method = RequestMethod.GET)
	public ModelAndView homepage() {
		ModelAndView mav = new ModelAndView();
		List<Restaurant> weekRests = restaurantRepository.getLastWeekAdded();
		for(Restaurant rest : weekRests) {
			//rest.setCalifications(calificationService.getCalifications(rest));
			HashMap<Integer,Calification> map = rest.getQualifications();
			rest.setCalifications(map);
		}
		mav.addObject("weekRests", weekRests);
		return mav;
	}
		
}

