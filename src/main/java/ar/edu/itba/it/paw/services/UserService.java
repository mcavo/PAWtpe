package ar.edu.itba.it.paw.services;

import ar.edu.itba.it.paw.forms.SignupForm;
import ar.edu.itba.it.paw.models.Credential;
import ar.edu.itba.it.paw.models.User;

public interface UserService {

	public Credential getUserCredentials(String mail, String pwd);
	
	public User getUserById(Credential cred);
	
	public User get(Integer id);
	
	public User signUp(User user,String pwd);
}
