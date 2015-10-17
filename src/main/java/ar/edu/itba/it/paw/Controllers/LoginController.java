package ar.edu.itba.it.paw.Controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.SessionUserManager;
import ar.edu.itba.it.paw.UserManager;
import ar.edu.itba.it.paw.models.Credential;
import ar.edu.itba.it.paw.models.User;
import ar.edu.itba.it.paw.services.RestaurantService;
import ar.edu.itba.it.paw.services.UserService;

@Controller
public class LoginController {

	private UserService UserService;
	
	@Autowired
	public LoginController(UserService userService){
		this.UserService = userService;
	}
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public ModelAndView get(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		User user = (User) request.getAttribute("user");
		if (user == null) {
			mav.setViewName("login");
		}else{
			mav.setViewName("homepage");
		}
		return mav;
	}
	
	@RequestMapping(value="", method = RequestMethod.POST)
	public String post(HttpServletRequest request, @RequestParam("email") String email, @RequestParam("pwd") String pwd) {
		ModelAndView mav = new ModelAndView();
		Credential cred = UserService.getUserCredentials(email, pwd);
		if(cred == null){
			return "redirect:";
		}else{
			User user = UserService.getUserById(cred);
			UserManager userManager = new SessionUserManager(request);
			userManager.setUser(user);
			return "redirect:../homepage";
		}
	}
}
