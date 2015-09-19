package ar.edu.itba.it.paw.services;

import java.util.HashMap;
import java.util.Map.Entry;

import ar.edu.itba.it.paw.DAO.OrderDAO;
import ar.edu.itba.it.paw.models.Dish;
import ar.edu.itba.it.paw.models.Restaurant;

public class OrderService {

	private boolean checkOrder(int usrId, Restaurant rest, HashMap<Dish, Integer> oMap){
		if(usrId <= 0 || rest == null || oMap == null){
			//app error
			return false;
		}
		if(oMap.isEmpty()){
			//excepcion pedido vacio
			return false;
		}

		for (Entry<Dish, Integer> set: oMap.entrySet()) {
			if(set.getValue() <= 0){
				//cant excepcion
				return false;
			}
			if(!OrderDAO.getInstance().checkDish(rest, set.getKey())){
				//dish no existe excp
				return false;
			}
		}
		return true;
	}
	
	public void sendOrder(int usrId, Restaurant rest, HashMap<Dish, Integer> oMap){
		if(!checkOrder(usrId, rest, oMap)){
			//show excp
			return;
		}
		OrderDAO.getInstance().sendOrder(usrId, rest.getId(), oMap, "enviado");
	}
	
	public Dish getDishByRestIdName(int restId, String nameProd){
		if(restId == 0 || nameProd == null || nameProd.isEmpty()){
			//app error
			return null;
		}
		return OrderDAO.getInstance().getDishByRestAndName(restId, nameProd);
	}
}
