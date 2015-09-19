package ar.edu.itba.it.paw.services;

import java.time.LocalDate;

import ar.edu.itba.it.paw.DAO.UserDAO;
import ar.edu.itba.it.paw.models.Address;
import ar.edu.itba.it.paw.models.User;

public class UserService {

	private static UserDAO instanceDAO = UserDAO.getInstance();
			
	public static String getUserId(String mail, String pwd){
		int id = instanceDAO.getUserId(mail, pwd);
		return String.valueOf(id);
	}
	
	public static User getUserById(String id){
		User user = instanceDAO.getUserById(Integer.valueOf(id));
		return user;
	}

	public static User signUp(String email, String pwd, String firstName, String lastName, LocalDate birth, boolean isManager, Address address) throws Exception{
		User user = new User(email, firstName, lastName, birth, isManager, address);
		return UserDAO.getInstance().setUser(user, pwd);
	}
}
