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
import ar.edu.itba.it.paw.forms.EditProfileForm;
import ar.edu.itba.it.paw.forms.SignupForm;
import ar.edu.itba.it.paw.models.User;
import ar.edu.itba.it.paw.repositories.AddressRepository;
import ar.edu.itba.it.paw.repositories.QuestionsRepository;
import ar.edu.itba.it.paw.repositories.UserRepository;
import ar.edu.itba.it.paw.validators.SignupValidator;

@Controller
public class ProfileController {
	
	private UserRepository userRepository;
	private SignupValidator signupValidator;
	private AddressRepository addressRepository;
	private QuestionsRepository questionsRepository;
	
	public ProfileController() { }

	@Autowired
	public ProfileController(UserRepository userRepo, SignupValidator signupValidator, AddressRepository addressRepository, QuestionsRepository questionRepository) {
		this.userRepository = userRepo;
		this.signupValidator = signupValidator;
		this.addressRepository=addressRepository;
		this.questionsRepository = questionRepository;
	}
		
	@SuppressWarnings("deprecation")
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView edit(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("profile");
		mav.addObject("neighList", addressRepository.getNeigh());
		mav.addObject("questList", questionsRepository.getQuestions());
		User user = (User) request.getAttribute("user");
		mav.addObject("user", user);
		mav.addObject("day",   user.getBirth().getDay());
		mav.addObject("month", user.getBirth().getMonth());
		mav.addObject("year", user.getBirth().getYear());
		mav.addObject("editProfileForm", new EditProfileForm());
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView update(HttpServletRequest request, SignupForm form, Errors e) {
		UserManager userManager = new SessionUserManager(request);
		if (userManager.existsUser()) {
			return new ModelAndView("redirect:../homepage/");
		}
		signupValidator.validate(form, e);
		ModelAndView mav = new ModelAndView("redirect:../profile/edit");
		if (e.hasErrors()) {
			request.setAttribute("message","Datos de registro inválidos");
			return mav;
		} /*else if (!userManager.setUser(this.userRepository.updateUser(form.build(),form.getPwd()))) {
			request.setAttribute("message","El usuario no puede ser editado");
			return mav;
		}*/
		return new ModelAndView("redirect:../homepage/");
	}
}
