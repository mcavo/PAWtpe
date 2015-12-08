package ar.edu.itba.it.paw.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.SessionUserManager;
import ar.edu.itba.it.paw.UserManager;
import ar.edu.itba.it.paw.domain.common.Message;
import ar.edu.itba.it.paw.domain.users.Credential;
import ar.edu.itba.it.paw.domain.users.CredentialRepository;
import ar.edu.itba.it.paw.domain.users.User;
import ar.edu.itba.it.paw.domain.users.UserRepository;
import ar.edu.itba.it.paw.exceptions.CredentialNoMatchException;

@Controller
public class LoginController {

	private UserRepository userRepository;
	private CredentialRepository credentialRepository;
	
	@Autowired
	public LoginController(UserRepository userRepo, CredentialRepository credentialRepo){
		this.userRepository = userRepo;
		this.credentialRepository = credentialRepo;
	}
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public ModelAndView get(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		User user = (User) request.getAttribute("user");
		if (user == null) {
			return mav;
		}else{
			return new ModelAndView("redirect:/bin/homepage");
		}
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public ModelAndView post(HttpServletRequest request, @RequestParam("email") String email, @RequestParam("pwd") String pwd) {
		Credential cred = null;
		ModelAndView mav = new ModelAndView();
		try {
			cred = credentialRepository.getCredentials(email, pwd);
		} catch (CredentialNoMatchException e) {
			mav.setViewName("login");
			mav.addObject("message", new Message("warning", "Mail o constraseña incorrectos."));
			return mav;
		};

		User user = userRepository.getUser(cred);
		if(user == null){
			mav.setViewName("login");				
			mav.addObject("message", new Message("warning", "Mail o constraseña incorrectos."));
			return mav;		
		}
		UserManager userManager = new SessionUserManager(request);
		userManager.setUser(user);
		return new ModelAndView("redirect:/bin/homepage");
	}
}
