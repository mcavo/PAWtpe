package ar.edu.itba.it.paw.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import ar.edu.itba.it.paw.models.User;

public class UserDAO {

	private static UserDAO instance = null;
	
	protected UserDAO(){
		
	}
	
	public static UserDAO getInstance(){
		if(instance == null){
			instance = new UserDAO();
		}
		return instance;
	}
	
	public static User getUser(String mail, String pwd){
		return new User(mail, null, null, false, null, null);
	}

	public String getUserId(String mail) {
		return "";
	}
	
}

