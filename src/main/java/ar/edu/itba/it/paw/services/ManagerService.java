package ar.edu.itba.it.paw.services;

import ar.edu.itba.it.paw.DAO.ManagerDAO;
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

	public static void addDish(String section, String dish, String price, String desc) {
		// TODO Auto-generated method stub
		
	}
}
