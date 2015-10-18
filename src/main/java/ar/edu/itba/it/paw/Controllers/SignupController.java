package ar.edu.itba.it.paw.Controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.SessionUserManager;
import ar.edu.itba.it.paw.UserManager;
import ar.edu.itba.it.paw.models.User;
import ar.edu.itba.it.paw.services.UserService;


@Controller
public class SignupController {
	private UserService userService;
	
	public SignupController() {	}
		
	@Autowired	
	public SignupController(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value="/signup", method = RequestMethod.GET)
	public ModelAndView validating(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		UserManager userManager = new SessionUserManager(request);
		if (userManager.existsUser()) {
			return new ModelAndView("redirect:../homepage/");
		}
		return mav;
	}
	
	@RequestMapping(value="/signup", method = RequestMethod.POST)
	public ModelAndView signup(HttpServletRequest request, @RequestParam("email") String email, @RequestParam("pwd") String pwd, @RequestParam("firstname") String firstname, @RequestParam("lastname") String lastname, @RequestParam("street") String street, @RequestParam("number") String number, @RequestParam("neighborhood") String neighborhood, @RequestParam("city") String city, @RequestParam("province") String province, @RequestParam("floor") String floor, @RequestParam("apartment") String apartment, @RequestParam("day") String day, @RequestParam("month") String month, @RequestParam("year") String year) {
		User user = null;
		try {
			user = this.userService.signUp(email, pwd, firstname, lastname, day, month, year, false, street, number, city, province, neighborhood, floor, apartment);
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("redirect:../signup/");
		}
		UserManager userManager = new SessionUserManager(request);
		userManager.setUser(user);
		return new ModelAndView("redirect:../homepage/");
	}
}
