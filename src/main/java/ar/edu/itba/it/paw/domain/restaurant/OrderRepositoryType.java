package ar.edu.itba.it.paw.domain.restaurant;

import java.util.HashMap;
import java.util.List;

import ar.edu.itba.it.paw.domain.users.User;
import ar.edu.itba.it.paw.exceptions.CreationDishException;
import ar.edu.itba.it.paw.exceptions.LoadOrderException;

public interface OrderRepositoryType {

	public boolean checkDish(Restaurant rest, Dish dish);
	public int sendOrder(User user, Restaurant rest, HashMap<Dish, Integer> oMap) throws CreationDishException;
	public Dish getDishByRestAndName(Restaurant rest, String nameProd);
	public List<Order> getHistory(Restaurant rest) throws LoadOrderException;
	public void updateStatus(User user, String orderId, String status);
}
