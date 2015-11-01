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
import ar.edu.itba.it.paw.exceptions.CredentialNoMatchException;
import ar.edu.itba.it.paw.models.Credential;
import ar.edu.itba.it.paw.models.User;
import ar.edu.itba.it.paw.repositories.CredentialRepository;
import ar.edu.itba.it.paw.repositories.UserRepository;

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
			return new ModelAndView("redirect:../homepage/");
		}
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String post(HttpServletRequest request, @RequestParam("email") String email, @RequestParam("pwd") String pwd) {
		Credential cred = null;
		try {
			cred = credentialRepository.getCredentials(email, pwd);
		} catch (CredentialNoMatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		if(cred == null){
			return "redirect:";
		}else{
			User user = userRepository.getUser(cred);
			if(user == null){
				//debería utilizar el get básico
				//app error
				return "";
			}
			UserManager userManager = new SessionUserManager(request);
			userManager.setUser(user);
			return "redirect:../homepage/";
		}
	}
}
