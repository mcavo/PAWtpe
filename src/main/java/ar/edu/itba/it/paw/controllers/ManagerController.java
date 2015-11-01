package ar.edu.itba.it.paw.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.models.Restaurant;
import ar.edu.itba.it.paw.models.User;
import ar.edu.itba.it.paw.repositories.OrderRepository;
import ar.edu.itba.it.paw.services.ManagerService;
import ar.edu.itba.it.paw.services.OrderService;

@Controller
public class ManagerController {

	private ManagerService managerService;
	private OrderRepository orderRepository;
	
	@Autowired
	public ManagerController(ManagerService service, OrderRepository orderRepo) {
		this.managerService = service;
		this.orderRepository = orderRepo;
	}
	
	@RequestMapping(value="/addDish", method = RequestMethod.GET)
	public ModelAndView addDish(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		User user = (User) request.getAttribute("user");
		if(user != null){
			request.setAttribute("rest", managerService.getRestaurant(user));
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
			managerService.addDish(managerService.getRestaurant(user), section, dish, price, description);
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
			Restaurant rest = managerService.getRestaurant(user);
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
}
