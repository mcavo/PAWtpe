package ar.edu.itba.it.paw.services;

import java.util.List;

import ar.edu.itba.it.paw.models.Credential;
import ar.edu.itba.it.paw.models.Restaurant;
import ar.edu.itba.it.paw.models.User;

public interface ManagerService {

	public Restaurant getRestByManager(User manager);
	
	public void addDish(Restaurant rest, String section, String dish, String price, String desc);
	
	public Restaurant getRestaurant(User usr);
	
	public Credential validateEmail(String email) throws Exception;
	
	public void SetManager(Credential cred,String name, String street, String number, String neighborhood, String city, String province, String floor, String apartment) throws Exception;
	
	public List<Credential> getManagersAvailables();
	
	public boolean addManager(String email, String restid);
	
}
