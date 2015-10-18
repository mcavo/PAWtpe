package ar.edu.itba.it.paw.Controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.SessionUserManager;
import ar.edu.itba.it.paw.UserManager;

@Controller
public class LogoutController {

	public LogoutController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request) {
		UserManager userManager = new SessionUserManager(request);
		if (userManager.existsUser()) {
			userManager.resetUser(null);
		}
		return new ModelAndView("redirect:../homepage/");
	}
}
