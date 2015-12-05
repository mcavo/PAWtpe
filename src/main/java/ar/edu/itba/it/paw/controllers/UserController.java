package ar.edu.itba.it.paw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.domain.common.Message;
import ar.edu.itba.it.paw.domain.users.CredentialRepository;
import ar.edu.itba.it.paw.domain.users.UserRepository;
import ar.edu.itba.it.paw.exceptions.NoCredentialException;

@Controller
public class UserController {

	private final UserRepository userRepository;
	private final CredentialRepository credentialRepository;
	
	@Autowired
	public UserController(UserRepository userRepository, CredentialRepository credentialRepo) {
		this.userRepository = userRepository;
		this.credentialRepository = credentialRepo;
	}
	
	@RequestMapping(value="/getq", method = RequestMethod.GET)
	public ModelAndView getq() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("getQuestion");
		return mav;
	}
	
	@RequestMapping(value="/getq", method = RequestMethod.POST)
	public ModelAndView getq(@RequestParam("email") String email) {
		ModelAndView mav = new ModelAndView();
		int userId = -1;
		try {
			userId = credentialRepository.getCredentialID(email);
			if(!userRepository.existsUser(userId)){
				userId = -1;
			}
		} catch (NoCredentialException e) {
			userId = -1;
		}
		if(userId <= 0){
			mav.addObject(new Message("warning", "No se encuentra disponible la recuperación para esa cuenta"));
			mav.setViewName("getQuestion");
		}else{
			mav.addObject("question", userRepository.getQuestion(userId));
			mav.addObject("userId", userId);
			mav.setViewName("askQuestion");
		}
		return mav;
	}
	
	@RequestMapping(value="/ask", method = RequestMethod.POST)
	public ModelAndView askQuestion(@RequestParam("userId") String userId,@RequestParam("question") String question,  @RequestParam("respuesta") String resp, @RequestParam("pwd") String pwd) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("homepage");
		if(userId.isEmpty()){
			mav.setViewName("getq");
			return mav;
		}
		if (!userRepository.updatePassword(userId, pwd, resp)) {
			mav.setViewName("askQuestion");
			mav.addObject("message", new Message("warning", "La respuesta ingresada no es válida"));
			mav.addObject("userId", userId);
			mav.addObject("question", question);
		}
		return mav;
	}
}
