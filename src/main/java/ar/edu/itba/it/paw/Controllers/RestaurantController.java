package ar.edu.itba.it.paw.Controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.models.Address;
import ar.edu.itba.it.paw.models.User;
import ar.edu.itba.it.paw.services.RestService;
import ar.edu.itba.it.paw.services.RestaurantService;

@Controller
public class RestaurantController {

	private final RestaurantService restaurantService;

	@Autowired
	public RestaurantController(RestaurantService restaurantService){
		this.restaurantService = restaurantService;
	}
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("rlist", restaurantService.getAllRestaurants());
		mav.setViewName("restaurantList");
		return mav;
	}
	
	@RequestMapping(value="/list", method = RequestMethod.POST)
	public ModelAndView list(HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("rlist", restaurantService.getRestaurants(req.getParameter("type")));
		mav.setViewName("restaurantList");
		return mav;
	}
	
	@RequestMapping(value="/details", method = RequestMethod.GET)
	public ModelAndView details(HttpServletRequest request, @RequestParam("name") String name, @RequestParam("srt") String street, @RequestParam("numb") String number, @RequestParam("neigh") String neighborhood, @RequestParam("city") String city, @RequestParam("prov") String province, @RequestParam("flr") String floor, @RequestParam("apt") String apartment) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("rest", restaurantService.getRestaurant(name, street, number, neighborhood, city, province, floor, apartment));
		mav.setViewName("showRestaurant");
		User user = (User) request.getAttribute("user");
		/*if(usr != null){
			req.setAttribute("okToQualify", CalificationServiceImpl.canQualify(rest, usr.getId()));
		}else{
			req.setAttribute("okToQualify", false);
		}*/
		return mav;
	}
	
	@RequestMapping(value="/menu", method = RequestMethod.GET)
	public ModelAndView menu(@RequestParam("name") String name, @RequestParam("srt") String street, @RequestParam("numb") String number, @RequestParam("neigh") String neighborhood, @RequestParam("city") String city, @RequestParam("prov") String province, @RequestParam("flr") String floor, @RequestParam("apt") String apartment) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("rest", restaurantService.getRestaurant(name, street, number, neighborhood, city, province, floor, apartment));
		mav.setViewName("menuRestaurant");
		return mav;
	}
}
