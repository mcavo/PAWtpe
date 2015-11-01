package ar.edu.itba.it.paw.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.itba.it.paw.Repositories.ManagerRepository;
import ar.edu.itba.it.paw.models.Credential;
import ar.edu.itba.it.paw.models.Restaurant;
import ar.edu.itba.it.paw.models.User;
import ar.edu.itba.it.paw.services.ManagerService;

@Service
public class ManagerServiceImpl implements ManagerService{

	private ManagerRepository managerRepository;
	private RestaurantServiceImpl restaurantService;
	
	public ManagerServiceImpl(){}
	
	@Autowired
	public ManagerServiceImpl(ManagerRepository managerRepo, RestaurantServiceImpl restaurantService){
		this.managerRepository = managerRepo;
		this.restaurantService = restaurantService;
	}
	
	public Restaurant getRestByManager(User manager){
		if(!manager.getIsManager()){
			//app error: no es manager
			return null;
		}
		return managerRepository.getRestByManagerId(manager.getId());
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
		if(!managerRepository.existsSection(section)){
			//no existe seccion
			return;
		}
		if(managerRepository.existsDish(dish)){
			//ya existe
			return;
		}
		managerRepository.addDish(rest.getId(), section, dish, Integer.valueOf(price), desc);
	}

	public Restaurant getRestaurant(User usr) {
		if(usr == null){
			//app exc
			return null;
		}
		return managerRepository.getRestaurant(usr.getId());
	}
	
	public Credential validateEmail(String email) throws Exception {
		Credential cred = null;
		/*try {
			ValidateDataService.validateMail(email);
			cred = credentialDAO.getCredentialsByEmail(email);
			if (cred == null || !(cred.getRol().equals("usuario"))){
				throw new Exception("bad parameter");	
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("bad parameter");
		}*/
		return cred;
	}
	
	public void SetManager(Credential cred,String name, String street, String number, String neighborhood, String city, String province, String floor, String apartment) throws Exception {
		Restaurant rest = restaurantService.getRestaurant(name, street, number, neighborhood, city, province, floor, apartment);
		if (rest == null) {
			return;
		}
		managerRepository.setManager(rest.getId(), cred.getId());
	}

	public List<Credential> getManagersAvailables() {
		//return credentialDAO.getManagersAvailables();
		return null;
	}

	public boolean addManager(String email, String restid) {
		Credential cred;
		try {
			cred = validateEmail(email);
			if (cred==null || !restaurantService.validateId(restid))
				return false;
			managerRepository.setManager(cred.getId(),Integer.parseInt(restid));
			return true;
		} catch (Exception e) {
			return false;
		}
		
		
		
	}
}
