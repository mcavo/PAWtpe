package ar.edu.itba.it.paw.services;

import ar.edu.itba.it.paw.models.Credential;
import ar.edu.itba.it.paw.models.User;

public interface UserService {

	public Credential getUserCredentials(String mail, String pwd);
	
	public User getUserById(Credential cred);
	
	public User get(Integer id);
	
	public User signUp(String email, String pwd, String firstName, String lastName, String day, String month, String year, boolean isManager, String street, String number, String city, String province, String neighborhood, String floor, String apartment) throws NumberFormatException, Exception;
	
}
