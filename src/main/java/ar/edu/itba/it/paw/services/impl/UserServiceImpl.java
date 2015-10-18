package ar.edu.itba.it.paw.services.impl;

import java.time.DateTimeException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.itba.it.paw.DAO.CredentialDAO;
import ar.edu.itba.it.paw.DAO.UserDAO;
import ar.edu.itba.it.paw.models.Address;
import ar.edu.itba.it.paw.models.Credential;
import ar.edu.itba.it.paw.models.User;
import ar.edu.itba.it.paw.services.UserService;

@Service
public class UserServiceImpl implements UserService{

	private UserDAO userDAO;
	private CredentialDAO credentialDAO;
			
	public UserServiceImpl(){}
	
	@Autowired
	public UserServiceImpl(UserDAO dao, CredentialDAO credentialDao){
		this.userDAO = dao;
		this.credentialDAO = credentialDao;
	}
	
	public Credential getUserCredentials(String mail, String pwd){
		return credentialDAO.getCredentials(mail, pwd);
	}
	
	public User getUserById(Credential cred){
		User user = userDAO.getUserById(cred.getId());
		if(user == null){
			//app error
			return null;
		}
		user.setManager(cred.getRol().equals("gerente"));
		user.setIsAdmin(cred.getRol().equals("admin"));
		return user;
	}

	public User signUp(String email, String pwd, String firstName, String lastName, String day, String month, String year, boolean isManager, String street, String number, String city, String province, String neighborhood, String floor, String apartment) throws DateTimeException, NumberFormatException, Exception {
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
		User user = createUser(email, firstName, lastName, birth);//new User(firstName, lastName, birth);
		user.setEmail(email);
		user.setManager(isManager);
		user.setAddress(address);
		return userDAO.setUser(user, pwd); //TODO: esta exceptción debería ser cambiada por una más personalizada.
	}

	private User createUser(String mail, String firstName, String lastName, LocalDate birth) {
		return new User(firstName, lastName, birth);
	}

	/*public static void setIfManager(User user, String rol) {
		user.setManager(rol.equals("gerente"));
		user.setIsAdmin(rol.equals("admin"));
	}*/
}
