package ar.edu.itba.it.paw.services;

import java.util.HashMap;
import java.util.List;

import ar.edu.itba.it.paw.models.Dish;
import ar.edu.itba.it.paw.models.Order;
import ar.edu.itba.it.paw.models.Restaurant;

public interface OrderService {

	public boolean sendOrder(int usrId, Restaurant rest, HashMap<Dish, String> oMap);
	
	public Dish getDishByRestIdName(int restId, String nameProd);
	
	public List<Order> getHistoryOrder(Restaurant rest);
}
