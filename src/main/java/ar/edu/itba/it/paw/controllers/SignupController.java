package ar.edu.itba.it.paw.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.SessionUserManager;
import ar.edu.itba.it.paw.UserManager;
import ar.edu.itba.it.paw.forms.SignupForm;
import ar.edu.itba.it.paw.services.UserService;
import ar.edu.itba.it.paw.validators.SignupValidator;


@Controller
public class SignupController {
	private UserService userService;
	private SignupValidator signupValidator;
	
	public SignupController() {	}
		
	@Autowired	
	public SignupController(UserService userService, SignupValidator signupValidator) {
		this.userService = userService;
		this.signupValidator = signupValidator;
	}
	
	@RequestMapping(value="signup", method = RequestMethod.GET)
	public ModelAndView validating(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		UserManager userManager = new SessionUserManager(request);
		if (userManager.existsUser()) {
			System.out.println("Hola1");
			return new ModelAndView();
		}
		System.out.println("Hola2");
		mav.addObject("signupForm", new SignupForm());
		return mav;
	}
	
	@RequestMapping(value="signup", method = RequestMethod.POST)
	public ModelAndView signup(HttpServletRequest request, SignupForm form, Errors e) {
		UserManager userManager = new SessionUserManager(request);
		if (userManager.existsUser()) {
			return new ModelAndView("redirect:../homepage/");
		}
		System.out.println("Hola3");
		signupValidator.validate(form, e);
		ModelAndView mav = new ModelAndView("redirect:../signup/");
		if (e.hasErrors()) {
			System.out.println("Hola4");
			request.setAttribute("message","Datos de registro inválidos");
			return mav;
		} else if (!userManager.setUser(this.userService.signUp(form.build(),form.getPwd()))) {
			System.out.println("Hola5");
			request.setAttribute("message","El usuario ya existe");
			return mav;
		}
		System.out.println("Hola6");
		return new ModelAndView("redirect:../homepage/");
	}
}
