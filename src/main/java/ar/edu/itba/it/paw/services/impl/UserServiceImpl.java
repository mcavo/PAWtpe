package ar.edu.itba.it.paw.services.impl;

import java.time.DateTimeException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.itba.it.paw.exceptions.CredentialNoMatchException;

import ar.edu.itba.it.paw.models.Address;
import ar.edu.itba.it.paw.models.Credential;
import ar.edu.itba.it.paw.models.User;
import ar.edu.itba.it.paw.repositories.CredentialRepository;
import ar.edu.itba.it.paw.repositories.UserRepository;
import ar.edu.itba.it.paw.services.UserService;

@Service
public class UserServiceImpl implements UserService{

	private UserRepository userRepository;
	private CredentialRepository credentialRepository;
			
	public UserServiceImpl(){}
	
	@Autowired
	public UserServiceImpl(UserRepository userRepo, CredentialRepository credentialRepository){
		this.userRepository = userRepo;
		this.credentialRepository = credentialRepository;
	}
	
	public Credential getUserCredentials(String mail, String pwd){
//		SessionFactory sf = HibernateConnection.getInstance().getSessionFactory();
//		CredentialRepository credentialRepository = new CredentialRepository(sf);
		try {
			return credentialRepository.getCredentials(mail, pwd);
		} catch (CredentialNoMatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
//		return credentialDAO.getCredentials(mail, pwd);
	}
	
	public User getUserById(Credential cred){
		User user = userRepository.getUserById(cred.getId());
		if(user == null){
			//debería utilizar el get básico
			//app error
			return null;
		}
		user.setManager(cred.getRol().equals("gerente"));
		user.setIsAdmin(cred.getRol().equals("admin"));
		return user;
	}

	@SuppressWarnings("deprecation")
	public User signUp(String email, String pwd, String firstName, String lastName, String day, String month, String year, boolean isManager, String street, String number, String city, String province, String neighborhood, String floor, String apartment) throws DateTimeException, NumberFormatException, Exception {
		//Get day
		Date birth = null;
		short dayV = Integer.valueOf(day).shortValue();
		short monthV = Integer.valueOf(month).shortValue();
		int yearV = Integer.valueOf(year).intValue();
		birth = new Date(yearV, monthV, dayV);	
		
		//Get the value
		int numberV = Integer.valueOf(number);

		int floorV;
		try {
			floorV = Integer.valueOf(floor);
		} catch (Exception e) {
			floorV = -1;
		}
		Address address = new Address(street, numberV, floorV, apartment, neighborhood, city, province);
		address.setFloor(floorV);	
		address.setApartment(apartment);
		
		//User user = new User(email, firstName, lastName, birth, isManager, address);
		User user = createUser(email, firstName, lastName, birth);//new User(firstName, lastName, birth);
		user.setManager(isManager);
		user.setAddress(address);
		//user.setDirId(address.getId());
		return userRepository.setUser(user, pwd); //TODO: esta exceptción debería ser cambiada por una más personalizada.
	}

	private User createUser(String mail, String firstName, String lastName, Date birth) {
		return new User(firstName, lastName, birth);
	}

	public static void setIfManager(User user, String rol) {
		user.setManager(rol.equals("gerente"));
		user.setIsAdmin(rol.equals("admin"));
	}

	@Override
	public User get(Integer id) {
		User user = userRepository.getUserById(id);
		Credential credential = credentialRepository.get(id);
		if(user == null){
			//app error
			return null;
		}
		user.setManager(credential.getRol().equals("gerente"));
		user.setIsAdmin(credential.getRol().equals("admin"));
		return user;
	}
}
