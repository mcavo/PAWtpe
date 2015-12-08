package ar.edu.itba.it.paw.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.domain.common.Message;
import ar.edu.itba.it.paw.domain.restaurant.OrderRepository;
import ar.edu.itba.it.paw.domain.restaurant.Restaurant;
import ar.edu.itba.it.paw.domain.restaurant.RestaurantRepository;
import ar.edu.itba.it.paw.domain.users.Credential;
import ar.edu.itba.it.paw.domain.users.ManagerRepository;
import ar.edu.itba.it.paw.domain.users.User;
import ar.edu.itba.it.paw.exceptions.LoadOrderException;
import ar.edu.itba.it.paw.exceptions.NoManagersAvailableException;
import ar.edu.itba.it.paw.exceptions.NoRestaurantException;

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
	
	@RequestMapping(value="/showOrders", method = RequestMethod.GET)
	public ModelAndView showOrders(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		User user = (User) request.getAttribute("user");
		if(user != null && user.getIsManager()){
			Restaurant rest;
			try {
				rest = managerRepository.getRestaurant(user);
				mav.addObject("rest", rest);
				request.setAttribute("olist", orderRepository.getHistory(rest));
			} catch (NoRestaurantException e) {
				mav.addObject("message", new Message("warning", "No hay ningún restaurant asignado a" + e.getManager().getFirstName()));
			} catch (LoadOrderException e) {
				mav.addObject("message", new Message("warning", "No hay órdenes para mostrar"));
			}
			mav.setViewName("showOrders");
		}else{
			return new ModelAndView("redirect:/bin/homepage");
		}
		return mav;
	}
	
	@RequestMapping(value="/showOrders", method = RequestMethod.POST)
	public String showOrders() {
		return "redirect:showOrders";
	}
	
	@RequestMapping(value="/addManager", method = RequestMethod.GET)
	public ModelAndView addManager(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		List<Restaurant> rlist = null;
		List<Credential> clist = null;
		User user = (User) request.getAttribute("user");
		if(user != null && user.getIsAdmin()){
			try {
				rlist = restaurantRepository.getAll();
				clist = managerRepository.getManagersAvailables();
				mav.addObject("rlist", rlist);
			} catch (NoManagersAvailableException e) {
				mav.addObject("message", new Message("warning","No hay managers disponibles"));
			}
			mav.addObject("clist", clist);
			mav.setViewName("addManager");
			return mav;
		}else{
			return new ModelAndView("redirect:/bin/homepage");
		}
	}

	@RequestMapping(value="/addManager", method = RequestMethod.POST)
	public String addManager(HttpServletRequest request, @RequestParam("manager-id") User manager, @RequestParam("restaurant-id") Restaurant rest) {
		User user = (User) request.getAttribute("user");
		if(user == null || manager==null || !user.getIsAdmin() || manager.getIsManager()) {
			return "redirect:addManager";
		}
		manager.setManager(true);
		managerRepository.setManager(manager);
		rest.addManager(manager);
		
		return "redirect:/bin/homepage";
	}
}
