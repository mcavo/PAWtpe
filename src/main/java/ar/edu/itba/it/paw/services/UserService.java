package ar.edu.itba.it.paw.services;

import java.time.DateTimeException;
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

	public static User signUp(String email, String pwd, String firstName, String lastName, String day, String month, String year, boolean isManager, String street, String number, String city, String province, String neighborhood, String floor, String apartment) throws DateTimeException, NumberFormatException, Exception {
		//Get day
		LocalDate birth = null;
		short dayV = Integer.valueOf(day).shortValue();
		short monthV = Integer.valueOf(month).shortValue();
		int yearV = Integer.valueOf(year).intValue();
		birth = LocalDate.of(yearV, monthV, dayV);	
		
		//Get the value
		int numberV = Integer.valueOf(number);

		int floorV;
		try {
			floorV = Integer.valueOf(floor);
		} catch (Exception e) {
			floorV = -1;
		}
		//address.setFloor(floorV);	
		//address.setApartment(apartment);
		Address address = new Address(street, numberV, floorV, apartment, neighborhood, city, province);
		
		//User user = new User(email, firstName, lastName, birth, isManager, address);
		User user = new User(firstName, lastName, birth);
		user.setEmail(email);
		user.setManager(isManager);
		user.setAddress(address);
		return UserDAO.getInstance().setUser(user, pwd); //TODO: esta exceptción debería ser cambiada por una más personalizada.
	}
}
