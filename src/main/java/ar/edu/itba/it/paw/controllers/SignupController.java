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
import ar.edu.itba.it.paw.repositories.AddressRepository;
import ar.edu.itba.it.paw.repositories.QuestionsRepository;
import ar.edu.itba.it.paw.repositories.UserRepository;
import ar.edu.itba.it.paw.validators.SignupValidator;


@Controller
public class SignupController {

	private UserRepository userRepository;
	private SignupValidator signupValidator;
	private AddressRepository addressRepository;
	private QuestionsRepository questionRepository;
	
	public SignupController() {	}
		

	@Autowired
	public SignupController(UserRepository userRepo, SignupValidator signupValidator, AddressRepository addressRepository, QuestionsRepository questionRepository) {
		this.userRepository = userRepo;
		this.signupValidator = signupValidator;
		this.addressRepository=addressRepository;
		this.questionRepository = questionRepository;
	}
	
	@RequestMapping(value="signup", method = RequestMethod.GET)
	public ModelAndView validating(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		UserManager userManager = new SessionUserManager(request);
		if (userManager.existsUser()) {
			return new ModelAndView("redirect:/bin/homepage");
		}
		mav.addObject("neighList", addressRepository.getNeigh());
		mav.addObject("questList", questionRepository.getQuestions());
		mav.addObject("signupForm", new SignupForm());
		return mav;
	}

	@RequestMapping(value="signup", method = RequestMethod.POST)
	public ModelAndView signup(HttpServletRequest request, SignupForm form, Errors e) {
		UserManager userManager = new SessionUserManager(request);
		if (userManager.existsUser()) {
			return new ModelAndView("redirect:/bin/homepage");
		}
		signupValidator.validate(form, e);
		ModelAndView mav = new ModelAndView("redirect:/bin/signup");
		if (e.hasErrors()) {
			request.setAttribute("message","Datos de registro inválidos");
			return mav;
		} else if (!userManager.setUser(this.userRepository.setUser(form.build(),form.getPwd()))) {
			request.setAttribute("message","El usuario ya existe");
			return mav;
		}

		return new ModelAndView("redirect:/bin/homepage");
	}
}
