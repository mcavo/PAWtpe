package ar.edu.itba.it.paw.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.apache.jasper.tagplugins.jstl.ForEach;

import ar.edu.itba.it.paw.DAO.OrderDAO;
import ar.edu.itba.it.paw.models.Dish;
import ar.edu.itba.it.paw.models.Order;
import ar.edu.itba.it.paw.models.Restaurant;

public class OrderService {

	private static boolean checkOrder(int usrId, Restaurant rest, HashMap<Dish, String> oMap){
		/*int usrIdV;
		try {
		usrIdV = Integer.valueOf(usrId);
		} catch (java.lang.NumberFormatException e) {
			return false;
		}
		if(usrIdV <= 0 || rest == null || oMap == null){
			//app error
			return false;
		}*/
		if(usrId <= 0 || rest == null || oMap == null){
			//app error
			return false;
		}
		
		if(oMap.isEmpty()){
			//excepcion pedido vacio
			return false;
		}

		for (Entry<Dish, String> set: oMap.entrySet()) {
			
			int cant; 
			try {
				cant = Integer.valueOf(set.getValue());
			} catch (java.lang.NumberFormatException e) {
				return false;
			}
			
			if(cant < 0){
				//cant excepcion
				return false;
			}
			OrderDAO.getInstance();
			if(!OrderDAO.checkDish(rest, set.getKey())){
				//dish no existe excp
				return false;
			}
		}
		return true;
	}
	
	public static boolean sendOrder(int usrId, Restaurant rest, HashMap<Dish, String> oMap){
		if(!checkOrder(usrId, rest, oMap)){
			//show excp
			return false;
		}
		HashMap<Dish,Integer> map = new HashMap<Dish,Integer>();
		for (Entry<Dish, String> set: oMap.entrySet()) {
			int aux = Integer.valueOf(set.getValue());
			if (aux!=0)
				map.put(set.getKey(), aux);
		}
		OrderDAO.getInstance().sendOrder(Integer.valueOf(usrId), rest.getId(), map);
		return true;
	}
	
	public static Dish getDishByRestIdName(int restId, String nameProd){
		if(restId == 0 || nameProd == null || nameProd.isEmpty()){
			//app error
			return null;
		}
		return OrderDAO.getInstance().getDishByRestAndName(restId, nameProd);
	}
	
	public static List<Order> getHistoryOrder(Restaurant rest){
		return OrderDAO.getInstance().getHistory(rest);
	}
}