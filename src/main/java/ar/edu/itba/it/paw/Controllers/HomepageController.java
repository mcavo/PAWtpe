package ar.edu.itba.it.paw.Controllers;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.models.Calification;
import ar.edu.itba.it.paw.models.Restaurant;
import ar.edu.itba.it.paw.services.CalificationService;
import ar.edu.itba.it.paw.services.RestaurantService;

@Controller
public class HomepageController {
	private RestaurantService restaurantService;
	private CalificationService calificationService; 
	
	@Autowired
	public HomepageController(RestaurantService restaurantService, CalificationService calificationService) {
		// TODO Auto-generated constructor stub
		this.restaurantService = restaurantService;
		this.calificationService = calificationService;
	}
	
	@RequestMapping(value="/homepage", method = RequestMethod.GET)
	public ModelAndView homepage() {
		ModelAndView mav = new ModelAndView();
		List<Restaurant> weekRests = restaurantService.getLastWeekRestaurants();
		for(Restaurant rest : weekRests) {
			rest.setCalifications(calificationService.getCalifications(rest));
			HashMap<Integer,Calification> map = calificationService.getCalifications(rest);
			rest.setCalifications(map);
		}
		mav.addObject("weekRests", weekRests);
		return mav;
	}
		
}

