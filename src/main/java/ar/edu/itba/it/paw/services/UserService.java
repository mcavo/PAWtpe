package ar.edu.itba.it.paw.services;

import ar.edu.itba.it.paw.DAO.UserDAO;
import ar.edu.itba.it.paw.models.User;

public class UserService {

	private static UserDAO instanceDAO = UserDAO.getInstance();
			
	public static String getUserId(String mail, String pwd){
		int id = instanceDAO.getUserId(mail, pwd);
		return String.valueOf(id);
	}
	
	public static User getUserById(String id){
		User user = instanceDAO.getUserById(Integer.valueOf(id));
		user.setAddress(instanceDAO.getUserAddressById(Integer.valueOf(id)));
		user.setEmail(instanceDAO.getUserMailById(Integer.valueOf(id)));
		return user;
	}
}
