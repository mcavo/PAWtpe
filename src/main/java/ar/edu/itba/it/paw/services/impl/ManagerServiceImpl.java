package ar.edu.itba.it.paw.services.impl;

import java.util.List;

import ar.edu.itba.it.paw.DAO.impl.CredentialDAOImpl;
import ar.edu.itba.it.paw.DAO.impl.ManagerDAOImpl;
import ar.edu.itba.it.paw.models.Credential;
import ar.edu.itba.it.paw.models.Restaurant;
import ar.edu.itba.it.paw.models.User;
import ar.edu.itba.it.paw.services.ManagerService;
import ar.edu.itba.it.paw.services.ValidateDataService;

public class ManagerServiceImpl implements ManagerService{

	private CredentialDAOImpl credentialDAO;
	private ManagerDAOImpl managerDAO;
	private RestaurantServiceImpl restaurantService;
	
	public ManagerServiceImpl(){}
	
	public ManagerServiceImpl(CredentialDAOImpl credentialDao, ManagerDAOImpl managerDao, RestaurantServiceImpl restaurantService){
		this.credentialDAO = credentialDao;
		this.managerDAO = managerDao;
		this.restaurantService = restaurantService;
	}
	
	public Restaurant getRestByManager(User manager){
		if(!manager.getIsManager()){
			//app error: no es manager
			return null;
		}
		return managerDAO.getRestByManagerId(manager.getId());
	}

	public void addDish(Restaurant rest, String section, String dish, String price, String desc) {
		if(Integer.valueOf(price) < 0 || dish == null || desc == null){
			//precio menor a 0 excp
			return;
		}
		if(rest == null){
			//app error
			return;
		}
		if(!managerDAO.existsSection(section)){
			//no existe seccion
			return;
		}
		if(managerDAO.existsDish(dish)){
			//ya existe
			return;
		}
		managerDAO.addDish(rest.getId(), section, dish, Integer.valueOf(price), desc);
	}

	public Restaurant getRestaurant(User usr) {
		if(usr == null){
			//app exc
			return null;
		}
		return managerDAO.getRestaurant(usr.getId());
	}
	
	public Credential validateEmail(String email) throws Exception {
		Credential cred = null;
		try {
			ValidateDataService.validateMail(email);
			cred = credentialDAO.getCredentialsByEmail(email);
			if (cred == null || !(cred.getRol().equals("usuario"))){
				throw new Exception("bad parameter");	
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("bad parameter");
		}
		return cred;
	}
	
	public void SetManager(Credential cred,String name, String street, String number, String neighborhood, String city, String province, String floor, String apartment) throws Exception {
		Restaurant rest = restaurantService.getRestaurant(name, street, number, neighborhood, city, province, floor, apartment);
		if (rest == null) {
			return;
		}
		managerDAO.setManager(rest.getId(), cred.getId());
	}

	public List<Credential> getManagersAvailables() {
		return credentialDAO.getManagersAvailables();
	}

	public boolean addManager(String email, String restid) {
		Credential cred;
		try {
			cred = validateEmail(email);
			if (cred==null || !restaurantService.validateId(restid))
				return false;
			managerDAO.setManager(cred.getId(),Integer.parseInt(restid));
			return true;
		} catch (Exception e) {
			return false;
		}
		
		
		
	}
}
