package ar.edu.itba.it.paw.services;

import ar.edu.itba.it.paw.DAO.CredentialDAO;
import ar.edu.itba.it.paw.DAO.ManagerDAO;
import ar.edu.itba.it.paw.models.Credential;
import ar.edu.itba.it.paw.models.Restaurant;
import ar.edu.itba.it.paw.models.User;

public class ManagerService {

	public static Restaurant getRestByManager(User manager){
		if(!manager.getIsManager()){
			//app error: no es manager
			return null;
		}
		return ManagerDAO.getInstance().getRestByManagerId(manager.getId());
	}

	public static void addDish(Restaurant rest, String section, String dish, String price, String desc) {
		if(Integer.valueOf(price) < 0 || dish == null || desc == null){
			//precio menor a 0 excp
			return;
		}
		if(rest == null){
			//app error
			return;
		}
		if(!ManagerDAO.getInstance().existsSection(section)){
			//no existe seccion
			return;
		}
		if(ManagerDAO.getInstance().existsDish(dish)){
			//ya existe
			return;
		}
		ManagerDAO.getInstance().addDish(rest.getId(), section, dish, Integer.valueOf(price), desc);
	}

	public static Restaurant getRestaurant(User usr) {
		if(usr == null){
			//app exc
			return null;
		}
		return ManagerDAO.getInstance().getRestaurant(usr.getId());
	}
	
	public static Credential validateEmail(String email) throws Exception {
		Credential cred = null;
		try {
			ValidateDataService.validateMail(email);
			cred = CredentialDAO.getInstance().getCredentialsByEmail(email);
			if (cred == null || !(cred.getRol().equals("usuario"))){
				throw new Exception("bad parameter");	
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("bad parameter");
		}
		return cred;
	}
	
	public static void SetManager(Credential cred,String name, String street, String number, String neighborhood, String city, String province, String floor, String apartment) throws Exception {
		Restaurant rest = RestService.getRestaurant(name, street, number, neighborhood, city, province, floor, apartment);
		if (rest == null) {
			return;
		}
		ManagerDAO.getInstance().setManager(rest.getId(), cred.getId());
		CredentialDAO.getInstance().setManager(cred.getId());
	}
}
