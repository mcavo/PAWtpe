package ar.edu.itba.it.paw.controllers;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.domain.address.AddressRepository;
import ar.edu.itba.it.paw.domain.common.Message;
import ar.edu.itba.it.paw.domain.restaurant.Calification;
import ar.edu.itba.it.paw.domain.restaurant.Dish;
import ar.edu.itba.it.paw.domain.restaurant.OrderRepository;
import ar.edu.itba.it.paw.domain.restaurant.Restaurant;
import ar.edu.itba.it.paw.domain.restaurant.RestaurantRepository;
import ar.edu.itba.it.paw.domain.users.ManagerRepository;
import ar.edu.itba.it.paw.domain.users.User;
import ar.edu.itba.it.paw.exceptions.NoRestaurantException;
import ar.edu.itba.it.paw.forms.RegisterRestForm;
import ar.edu.itba.it.paw.validators.RegisterRestValidator;

@Controller
public class RestaurantController {
	
	private final OrderRepository orderRepository;
	private final RestaurantRepository restaurantRepository;
	private final ManagerRepository managerRepository;
	private final AddressRepository addressRepository;
	private final RegisterRestValidator registerRestValidator;
	
	
	@Autowired
	public RestaurantController(RestaurantRepository restaurantRepo, ManagerRepository managerRepo, OrderRepository orderRepo, AddressRepository addressRepository, RegisterRestValidator registerRestValidator){
		this.orderRepository = orderRepo;
		this.restaurantRepository = restaurantRepo;
		this.managerRepository = managerRepo;
		this.addressRepository=addressRepository;
		this.registerRestValidator=registerRestValidator;
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
			request.setAttribute("okToQualify", !restaurant.hasCalificationByUser(user));
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
	public ModelAndView details(HttpServletRequest request, @RequestParam("code") Restaurant restaurant, @RequestParam("rating") String stars, @RequestParam(value="comment", required=false) String comments) {	
		ModelAndView mav = new ModelAndView();
		User user = (User) request.getAttribute("user");
		mav.addObject("rest", restaurant);
		mav.setViewName("showRestaurant");
		try {
			Calification q = new Calification(Integer.valueOf(stars), comments);
			q.setUser(user);
			q.setRestaurant(restaurant);
			restaurant.addCalification(q);
		} catch (Exception e) {
			mav.addObject("message", new Message("warning", "No se pudo realizar la calificación"));
		}
		
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
	public ModelAndView sendOrder(HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		String restId = req.getParameter("restId");
		Restaurant rest = restaurantRepository.getById(Integer.valueOf(restId));
		User user = (User) req.getAttribute("user");
		if(user == null || rest == null){
			return new ModelAndView("redirect:/bin/homepage");
		}
		mav.addObject("rest", rest);
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
			req.setAttribute("message",new Message("warning", "El monto minimo de pedido es" + rest.getMontomin().toString()));
			mav.addObject("code", rest.getId());
			mav.setViewName("redirect:menu");
			return mav;
		}
		mav.addObject("orderId", orderId.toString());
		mav.addObject("newOrderId", (orderId>0));
		mav.setViewName("showRestaurant");
		return mav;
	}
	
	@RequestMapping(value="/status", method = RequestMethod.GET)
	public ModelAndView status() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("setStatus");
		return mav;
	}
	
	@RequestMapping(value="/status", method = RequestMethod.POST)
	public String status(HttpServletRequest request) {
		User user = (User) request.getAttribute("user");
		if(user == null){
			return "redirect:/bin/homepage";
		}
		orderRepository.updateStatus(user, request.getParameter("orderID"), request.getParameter("estado"));
		return "redirect:/bin/homepage";
	}
	
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public ModelAndView register(HttpServletRequest request) {
		User user = (User) request.getAttribute("user");
		if(user == null || !user.getIsAdmin()){
			return new ModelAndView("redirect:/bin/homepage");
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("registerRestaurant");
		mav.addObject("neighList", addressRepository.getNeigh());
		mav.addObject("registerRestForm", new RegisterRestForm());

		return mav;
	}
	
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public ModelAndView register(HttpServletRequest request, RegisterRestForm form, Errors e) {
		User user = (User) request.getAttribute("user");
		if(user == null || !user.getIsAdmin()){
			return new ModelAndView("redirect:/bin/homepage");
		}
		registerRestValidator.validate(form, e);
		ModelAndView mav = new ModelAndView("redirect:/bin/restaurant/register");
		if (e.hasErrors()) {
			request.setAttribute("message","Datos de registro de restaurante inválidos");
			return mav;
		} else if (!restaurantRepository.setRestaurant(form.build(this.addressRepository))) {
			request.setAttribute("message","El restaurant ya esxiste");
			return mav;
		}

		return new ModelAndView("redirect:/bin/homepage");
	}
	
	@RequestMapping(value="/addDish", method = RequestMethod.GET)
	public ModelAndView addDish(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		User user = (User) request.getAttribute("user");
		if(user != null && user.getIsManager()){
			try {
				request.setAttribute("rest", managerRepository.getRestaurant(user));
			} catch (NoRestaurantException e) {
				mav.addObject("warning", "No hay restaurants disponibles para el usuario: " + e.getManager().getFirstName());
			}
			mav.setViewName("addDish");
		}else{
			return new ModelAndView("redirect:/bin/homepage");
		}
		return mav;
	}
	
	@RequestMapping(value="/addDish", method = RequestMethod.POST)
	public ModelAndView addDish(HttpServletRequest request, @RequestParam("section") String section, @RequestParam("dish") String dish, @RequestParam("price") String price, @RequestParam("description") String description) {
		ModelAndView mav = new ModelAndView();
		User user = (User) request.getAttribute("user");
		if(user != null && user.getIsManager()){
			try {
				managerRepository.addDish(managerRepository.getRestaurant(user), section, dish, Integer.valueOf(price), description);
			}catch (NoRestaurantException e) {
				mav.addObject("warning", "No hay restaurants disponibles para el usuario: " + e.getManager().getFirstName());
			}
			mav.setViewName("addDish");
		}else{
			return new ModelAndView("redirect:/bin/homepage");
		}
		return mav;
	}
	
	@RequestMapping(value="/homepage", method = RequestMethod.GET)
	public ModelAndView homepage() {
		ModelAndView mav = new ModelAndView();
		List<Restaurant> weekRests = restaurantRepository.getLastWeekAdded();
		mav.addObject("weekRests", weekRests);
		return mav;
	}
}
