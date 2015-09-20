package ar.edu.itba.it.paw.services;

import java.util.HashMap;

import ar.edu.itba.it.paw.DAO.CalificationDAO;
import ar.edu.itba.it.paw.models.Calification;
import ar.edu.itba.it.paw.models.Restaurant;
import ar.edu.itba.it.paw.models.User;

public class CalificationService {

	public static boolean canQualify(Restaurant r, int usrId){
		return !r.getQualifications().keySet().contains(usrId);
	}
	
	public static void addCalification(int usrId, Restaurant rest, String stars, String comments) {
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
		CalificationDAO.getInstance().addCalification(usrId, restId, rate, comments);
		Calification q = new Calification(rate, comments);
		RestService.addCalification(usrId, rest, q);
	}

	public static HashMap<Integer, Calification> getCalificationsByRestId(int restId) {
		return CalificationDAO.getInstance().getCalificationsByRestId(restId);
	}
}
