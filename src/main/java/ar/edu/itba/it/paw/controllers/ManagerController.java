package ar.edu.itba.it.paw.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.models.Credential;
import ar.edu.itba.it.paw.models.Restaurant;
import ar.edu.itba.it.paw.models.User;
import ar.edu.itba.it.paw.repositories.ManagerRepository;
import ar.edu.itba.it.paw.repositories.OrderRepository;
import ar.edu.itba.it.paw.repositories.RestaurantRepository;

@Controller
public class ManagerController {

	private ManagerRepository managerRepository;
	private OrderRepository orderRepository;
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	public ManagerController(ManagerRepository managerRepo, OrderRepository orderRepo, RestaurantRepository restuarantRepository) {
		this.managerRepository = managerRepo;
		this.orderRepository = orderRepo;
		this.restaurantRepository = restuarantRepository;
	}
	
	@RequestMapping(value="/addDish", method = RequestMethod.GET)
	public ModelAndView addDish(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		User user = (User) request.getAttribute("user");
		if(user != null){
			request.setAttribute("rest", managerRepository.getRestaurant(user));
			mav.setViewName("addDish");
		}else{
			return new ModelAndView("redirect:../homepage/");
		}
		return mav;
	}
	
	@RequestMapping(value="/addDish", method = RequestMethod.POST)
	public ModelAndView addDish(HttpServletRequest request, @RequestParam("section") String section, @RequestParam("dish") String dish, @RequestParam("price") String price, @RequestParam("description") String description) {
		ModelAndView mav = new ModelAndView();
		User user = (User) request.getAttribute("user");
		if(user != null){
			managerRepository.addDish(managerRepository.getRestaurant(user), section, dish, Integer.valueOf(price), description);
			mav.setViewName("addDish");
		}else{
			return new ModelAndView("redirect:../homepage/");
		}
		return mav;
	}
	
	@RequestMapping(value="/showOrders", method = RequestMethod.GET)
	public ModelAndView showOrders(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		User user = (User) request.getAttribute("user");
		if(user != null){
			Restaurant rest = managerRepository.getRestaurant(user);
			mav.addObject("rest", rest);
			request.setAttribute("olist", orderRepository.getHistory(rest));
			mav.setViewName("showOrders");
		}else{
			return new ModelAndView("redirect:../homepage/");
		}
		return mav;
	}
	
	@RequestMapping(value="/showOrders", method = RequestMethod.POST)
	public String showOrders() {
		return "redirect:showOrders";
	}
	
	@RequestMapping(value="/addManager", method = RequestMethod.GET)
	public ModelAndView addManager() {
		ModelAndView mav = new ModelAndView();
		List<Restaurant> rlist;
		List<Credential> clist;
		rlist = restaurantRepository.getAll();
		clist = managerRepository.getManagersAvailables();
		mav.addObject("rlist", rlist);
		mav.addObject("clist", clist);
		mav.setViewName("addManager");
		return mav;
	}

	@RequestMapping(value="/addManager", method = RequestMethod.POST)
	public String addManager(@RequestParam("manager-id") int userid, @RequestParam("restaurant-id") int restid) {
		//Falta alguna validación más??
		if (!managerRepository.addManager(userid, restid)) {
			return "redirect:addManager";
		}
		return "redirect:../homepage/";
	}
}
