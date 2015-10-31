package ar.edu.itba.it.paw.DAO;

import java.util.HashMap;
import java.util.List;

import ar.edu.itba.it.paw.models.Dish;
import ar.edu.itba.it.paw.models.Order;
import ar.edu.itba.it.paw.models.Restaurant;

public interface OrderDAO {

	public boolean checkDish(Restaurant rest, Dish dish);
	
	public void sendOrder(int usrId, Restaurant rest, HashMap<Dish, Integer> oMap);
	
	public Dish getDishByRestAndName(int restId, String nameProd);
	
	public List<Order> getHistory(Restaurant rest);
	
	
}
