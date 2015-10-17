package ar.edu.itba.it.paw.services.impl;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import ar.edu.itba.it.paw.DAO.CalificationDAO;
import ar.edu.itba.it.paw.models.Calification;
import ar.edu.itba.it.paw.models.Restaurant;
import ar.edu.itba.it.paw.services.CalificationService;
import ar.edu.itba.it.paw.services.RestaurantService;

@Service
public class CalificationServiceImpl implements CalificationService{

	private CalificationDAO calificationDAO;
	private RestaurantService restaurantService;
	
	public CalificationServiceImpl(){}
	
	public CalificationServiceImpl(CalificationDAO calificationDao, RestaurantService restaurantService){
		this.calificationDAO = calificationDao;
		this.restaurantService = restaurantService;
	}
	
	public boolean canQualify(Restaurant r, int usrId){
		return !r.getQualifications().keySet().contains(usrId);
	}
	
	public void addCalification(int usrId, Restaurant rest, String stars, String comments) {
		int restId = rest.getId();
		//int userId = Integer.valueOf(usrId);
		if(usrId == 0 || restId == 0){
			//app error
			return;
		}
		int rate = Integer.valueOf(stars);
		if(rate < 0 || rate > 5){
			//app error
			return;
		}
		if(comments.isEmpty()){
			//tirar excepcion de comentario vacio
			return;
		}
		calificationDAO.addCalification(usrId, restId, rate, comments);
		Calification q = new Calification(rate, comments);
		restaurantService.addCalification(usrId, rest, q);
	}

	public HashMap<Integer, Calification> getCalifications(Restaurant restaurant) {
		if(restaurant == null){
			//app error
			return null;
		}
		return calificationDAO.getCalifications(restaurant);
	}
}
