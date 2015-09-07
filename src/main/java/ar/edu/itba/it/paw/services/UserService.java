package ar.edu.itba.it.paw.services;

import ar.edu.itba.it.paw.UserDAO;
import ar.edu.itba.it.paw.models.User;

public class UserService {

	public static User getUser(String mail, String pwd){
		return UserDAO.getInstance().getUser(mail, pwd);
	}
	
	public static String getUserId(String mail){
		return UserDAO.getInstance().getUserId(mail);
	} 
}
