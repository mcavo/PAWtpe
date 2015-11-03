package ar.edu.itba.it.paw.controllers;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.SessionUserManager;
import ar.edu.itba.it.paw.UserManager;
import ar.edu.itba.it.paw.models.Calification;
import ar.edu.itba.it.paw.models.Dish;
import ar.edu.itba.it.paw.models.Restaurant;
import ar.edu.itba.it.paw.models.User;
import ar.edu.itba.it.paw.repositories.CalificationRepository;
import ar.edu.itba.it.paw.repositories.OrderRepository;
import ar.edu.itba.it.paw.repositories.RestaurantRepository;

@Controller
public class RestaurantController {
	
	private final CalificationRepository calificationRepository;
	private final OrderRepository orderRepository;
	private final RestaurantRepository restaurantRepository;
	
	@Autowired
	public RestaurantController(RestaurantRepository restaurantRepo, CalificationRepository calificationRepo, OrderRepository orderRepo){
		this.calificationRepository = calificationRepo;
		this.orderRepository = orderRepo;
		this.restaurantRepository = restaurantRepo;
	}
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("rlist", restaurantRepository.getAll());
		mav.setViewName("restaurantList");
		return mav;
	}
	
	@RequestMapping(value="/list", method = RequestMethod.POST)
	public ModelAndView list(HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("rlist", restaurantRepository.filterBy(req.getParameter("type")));
		mav.setViewName("restaurantList");
		return mav;
	}

	@RequestMapping(value="/details", method = RequestMethod.GET)
	public ModelAndView details(HttpServletRequest request, @RequestParam("code") Restaurant restaurant) {	
		ModelAndView mav = new ModelAndView();
		mav.addObject("rest", restaurant);
		mav.setViewName("showRestaurant");
		User user = (User) request.getAttribute("user");
		if(user != null){
			request.setAttribute("okToQualify", !restaurant.getQualifications().keySet().contains(user.getId()));
		}else{
			request.setAttribute("okToQualify", false);
		}
		return mav;
	}
	
	@RequestMapping(value="/popular", method = RequestMethod.GET)
	public ModelAndView popular() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("rlist", restaurantRepository.getMostPopular());
		mav.setViewName("restaurantList");
		return mav;
	}
	
	@RequestMapping(value="/details", method = RequestMethod.POST) 
	public ModelAndView details(HttpServletRequest request, @RequestParam("code") Restaurant restaurant, @RequestParam("rating") String stars, @RequestParam(value="comment", required=false) String comments) throws Exception {	
		ModelAndView mav = new ModelAndView();
		User user = (User) request.getAttribute("user");
		//rest.setCalifications(calificationService.getCalifications(rest));
		mav.addObject("rest", restaurant);
		mav.setViewName("showRestaurant");
		/*UserManager userManager = new SessionUserManager(request);
		if (!userManager.existsUser()) {
			throw new Exception("No hay un usuario loggeado");
		}*/
		calificationRepository.addCalification(user, restaurant, stars, comments);
		restaurant.getQualifications().put(user.getId(), new Calification(Integer.valueOf(stars), comments));
		request.setAttribute("rest", restaurant);
		
		return mav;
	}
	
	@RequestMapping(value="/menu", method = RequestMethod.GET)
	public ModelAndView menu(HttpServletRequest request, @RequestParam("code") Restaurant restaurant) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("rest", restaurant);
		User user = (User) request.getAttribute("user");
		boolean ok2order = false;
		if(user != null){
			ok2order = restaurantRepository.userCanOrder(user, restaurant);
		}
		mav.addObject("canOrder", ok2order);
		mav.setViewName("menuRestaurant");
		return mav;
	}
	
	@RequestMapping(value="/sendOrder", method = RequestMethod.POST)
	public ModelAndView sendOrder(HttpServletRequest req/*, @RequestParam("name") String name, @RequestParam("srt") String street, @RequestParam("numb") String number, @RequestParam("neigh") String neighborhood, @RequestParam("city") String city, @RequestParam("prov") String province, @RequestParam("flr") String floor, @RequestParam("apt") String apartment*/) {
		ModelAndView mav = new ModelAndView();
		String restId = req.getParameter("restId");
		Restaurant rest = restaurantRepository.getById(Integer.valueOf(restId));
		mav.addObject("rest", rest);
		User user = (User) req.getAttribute("user");
		if(user == null){
			return new ModelAndView("redirect:../homepage/");
		}
		Enumeration en = req.getParameterNames();
		HashMap<Dish, Integer> map = new HashMap<Dish, Integer>();
		while (en.hasMoreElements()) {
			Object objOri = en.nextElement();
			String param = (String) objOri;
			String value = req.getParameter(param);
			
			Dish d = orderRepository.getDishByRestAndName(rest,param);
			if(d != null)
				map.put(d,Integer.valueOf(value));	
		}
		Integer orderId = orderRepository.sendOrder(user, rest, map);
		if(orderId <= 0){
			return new ModelAndView("redirect:../homepage/");
		}
		/*if(!orderRepository.sendOrder(user.getId(), rest, map)){
			return new ModelAndView("redirect:../homepage/");
		}*/
		mav.addObject("orderId", orderId.toString());
		mav.addObject("newOrderId", (orderId>0));
		mav.setViewName("showRestaurant");
		return mav;
	}
	
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public ModelAndView register(@RequestParam("name") String name, @RequestParam("street") String street, @RequestParam("number") String number, @RequestParam("neighborhood") String neighborhood, @RequestParam("city") String city, @RequestParam("province") String province, @RequestParam("floor") String floor, @RequestParam("apartment") String apartment, @RequestParam(value="description", required=false) String description,  @RequestParam("from") String from,  @RequestParam("to") String to,  @RequestParam("minimum") String minimum,  @RequestParam("cost") String cost, @RequestParam("checkboxes") String[] types) {
		ModelAndView mav = new ModelAndView();
		try {
			restaurantRepository.setRestaurant(name, description, types, from, to, street, number, city, province, floor, apartment, neighborhood, minimum, cost);
		} catch (Exception e) {
			e.printStackTrace();
			return mav;
		}
		return new ModelAndView("redirect:../homepage/");
	}
	
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("registerRestaurant");
		return mav;
	}
	
	@RequestMapping(value="/homepage", method = RequestMethod.GET)
	public ModelAndView homepage() {
		ModelAndView mav = new ModelAndView();
		List<Restaurant> weekRests = restaurantRepository.getLastWeekAdded();
		for(Restaurant rest : weekRests) {
			HashMap<Integer,Calification> map = rest.getQualifications();
			rest.setCalifications(map);
		}
		mav.addObject("weekRests", weekRests);
		return mav;
	}
}
