package ar.edu.itba.it.paw.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.itba.it.paw.Repositories.OrderRepository;
import ar.edu.itba.it.paw.models.Dish;
import ar.edu.itba.it.paw.models.Order;
import ar.edu.itba.it.paw.models.Restaurant;
import ar.edu.itba.it.paw.services.OrderService;

@Service
public class OrderServiceImpl implements OrderService{

	private OrderRepository repository;
	
	public OrderServiceImpl(){}
	
	@Autowired
	public OrderServiceImpl(OrderRepository orderRepository){
		this.repository = orderRepository;
	}
	
	private boolean checkOrder(int usrId, Restaurant rest, HashMap<Dish, String> oMap){
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

		int total = 0;
		for (Entry<Dish, String> set: oMap.entrySet()) {
			
			int cant; 
			Dish dish = set.getKey();
			try {
				cant = Integer.valueOf(set.getValue());
			} catch (java.lang.NumberFormatException e) {
				return false;
			}
			
			if(cant < 0){
				//cant excepcion
				return false;
			}
			if(!this.repository.checkDish(rest, dish)){
				//dish no existe excp
				return false;
			}
			total += dish.getPrice()*cant;
		}
		if(total < rest.getMontomin()){
			//monto minimo no llegado
			return false;
		}
		return true;
	}
	
	public boolean sendOrder(int usrId, Restaurant rest, HashMap<Dish, String> oMap){
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
		this.repository.sendOrder(Integer.valueOf(usrId), rest, map);
		return true;
	}
	
	/*public Dish getDishByRestIdName(int restId, String nameProd){
		if(restId == 0 || nameProd == null || nameProd.isEmpty()){
			//app error
			return null;
		}
		return this.repository.getDishByRestAndName(restId, nameProd);
	}*/
	
	public List<Order> getHistoryOrder(Restaurant rest){
		return this.repository.getHistory(rest);
	}
}