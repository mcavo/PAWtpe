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
import ar.edu.itba.it.paw.domain.address.AddressRepository;
import ar.edu.itba.it.paw.domain.users.QuestionsRepository;
import ar.edu.itba.it.paw.domain.users.User;
import ar.edu.itba.it.paw.domain.users.UserRepository;
import ar.edu.itba.it.paw.forms.EditProfileForm;
import ar.edu.itba.it.paw.services.DateService;
import ar.edu.itba.it.paw.validators.ProfileValidator;

@Controller
public class ProfileController {
	
	private UserRepository userRepository;
	private ProfileValidator profileValidator;
	private AddressRepository addressRepository;
	private QuestionsRepository questionsRepository;
	
	public ProfileController() { }

	@Autowired
	public ProfileController(UserRepository userRepo, ProfileValidator profileValidator, AddressRepository addressRepository, QuestionsRepository questionRepository) {
		this.userRepository = userRepo;
		this.profileValidator = profileValidator;
		this.addressRepository=addressRepository;
		this.questionsRepository = questionRepository;
	}
		
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView edit(HttpServletRequest request) {
		User user = (User) request.getAttribute("user");
		if (user == null) {
			return new ModelAndView("redirect:/bin/homepage/");
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("profile");
		mav.addObject("neighList", addressRepository.getNeigh());
		mav.addObject("questList", questionsRepository.getQuestions());
		mav.addObject("user", user);
		mav.addObject("day",   DateService.getDayOfMonth(user.getBirth()));
		mav.addObject("month", DateService.getMonth(user.getBirth()));
		mav.addObject("year", DateService.getYear(user.getBirth()));
		mav.addObject("editProfileForm", new EditProfileForm());
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView update(HttpServletRequest request, EditProfileForm form, Errors e) {
		UserManager userManager = new SessionUserManager(request);
		profileValidator.validate(form, e);
		ModelAndView mav = new ModelAndView("redirect:/bin/profile/edit");
		User us = form.build();
		User curr = (User) request.getAttribute("user");
		us.setId(curr.getId());
		us.setManager(curr.getIsManager());
		us.getAddress().setId(curr.getAddress().getId());
		if (e.hasErrors()) {
			request.setAttribute("message","Datos de registro inválidos");
			return mav;
		} else if (!userManager.setUser(this.userRepository.updateUser(us))) {
			request.setAttribute("message","El usuario no puede ser editado");
			return mav;
		}
		return new ModelAndView("redirect:/bin/homepage/");
	}
}
