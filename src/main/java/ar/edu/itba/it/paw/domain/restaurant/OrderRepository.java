package ar.edu.itba.it.paw.domain.restaurant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.domain.common.AbstractHibernateRepository;
import ar.edu.itba.it.paw.domain.users.User;
import ar.edu.itba.it.paw.domain.users.UserRepository;
import ar.edu.itba.it.paw.exceptions.CreationDishException;
import ar.edu.itba.it.paw.exceptions.LoadOrderException;

@Repository
public class OrderRepository extends AbstractHibernateRepository implements OrderRepositoryType{

	private UserRepository userRepository;
	
	@Autowired
	public OrderRepository(SessionFactory sessionFactory, UserRepository userRepo) {
		super(sessionFactory);
		this.userRepository = userRepo;
	}

	public boolean checkDish(Restaurant rest, Dish dish) {
		List<Dish> dishes = find("FROM Dish WHERE restid = ? and nombre like ? and descripcion like ? and precio = ?", rest.getId(), dish.getProduct(), dish.getDescription(), dish.getPrice());
		return !dishes.isEmpty();
	}
	
	public int sendOrder(User user, Restaurant rest, HashMap<Dish, Integer> oMap) throws CreationDishException {
		if(!checkOrder(user.getId(), rest, oMap)){
			return -1;
		}
		HashMap<Dish,Integer> map = new HashMap<Dish,Integer>();
		for (Entry<Dish, Integer> set: oMap.entrySet()) {
			int aux = set.getValue();
			if (aux!=0)
				map.put(set.getKey(), aux);
		}
		Order order = new Order(rest, this.userRepository.getUserById(user.getId()), 0);
		int id = (int) save(order);
		loadProducts(id, oMap);
		return id;
	}
	
	private boolean checkOrder(int usrId, Restaurant rest, HashMap<Dish, Integer> oMap){
		if(usrId <= 0 || rest == null || oMap == null){
			return false;
		}
		
		if(oMap.isEmpty()){
			return false;
		}

		int total = 0;
		for (Entry<Dish, Integer> set: oMap.entrySet()) {
			
			int cant; 
			Dish dish = set.getKey();
				cant = set.getValue();
			
			if(cant < 0){
				return false;
			}
			if(!checkDish(rest, dish)){
				return false;
			}
			total += dish.getPrice()*cant;
		}
		if(total < rest.getMinamount()){
			return false;
		}
		return true;
	}
	
	private void loadProducts(int orderId, HashMap<Dish, Integer> oMap) throws CreationDishException {
		for (Entry<Dish, Integer> set : oMap.entrySet()) {
			loadByOne(orderId, set.getKey().getId(), set.getValue());
		}
	}

	private void loadByOne(int orderId, int dishId, int cant) throws CreationDishException {
		Session session=null;
	    try 
	    {
		    Session sessionSQL = super.getSession();
		    SQLQuery query = (SQLQuery) sessionSQL.createSQLQuery("insert into prodPedidos (pedid, platoid, cant) VALUES (?, ?, ?)");
		    query.setParameter(0, orderId); 
		    query.setParameter(1, dishId);
		    query.setParameter(2, cant);
		    query.executeUpdate();
	    }
	    catch(Exception e)
	    {
	    	throw new CreationDishException();
	    }
	    finally
	    {
	        if(session !=null && session.isOpen())
	        {
	          session.close();
	          session=null;
	        }
	    }
	}
	
	public Dish getDishByRestAndName(Restaurant rest, String nameProd) {
		List<Dish> dishes = find("from Dish where restid = ? and nombre like ?", rest.getId(), nameProd);
		if(dishes.isEmpty()){
			return null;
		}
		return dishes.get(0);
	}
	
	public List<Order> getHistory(Restaurant rest) throws LoadOrderException {
		List<Order> history = find("from Order where restid = ?", rest.getId());
		for (Order order : history) {
			order.setOrdlist(getOrderList(order.getId()));
		}
		return history;
	}


	@SuppressWarnings("unchecked")
	private Map<Dish, Integer> getOrderList(int orderId) throws LoadOrderException {
		Map<Dish, Integer> order = new HashMap<Dish, Integer>();
		
		Session session=null;
	    try 
	    {
		    Session sessionSQL = super.getSession();
		    SQLQuery query = (SQLQuery) sessionSQL.createSQLQuery("select * from prodPedidos where pedid = ?").setParameter(0, orderId); 
		    List<Object[]> rows = query.list();
		    
		    int platoid;
		    int cant;
		    for (Object[] row: rows) {
		    	platoid = (int) row[1];
		    	cant = (int) row[2];
		    	order.put(getDishById(platoid), cant);
		    }
	    }
	    catch(Exception e)
	    {
	    	throw new LoadOrderException();
	    }
	    finally
	    {
	        if(session !=null && session.isOpen())
	        {
	          session.close();
	          session=null;
	        }
	    }
	    return order;
	}

	private Dish getDishById(int id) {
		return get(Dish.class, id);
	}

	public void updateStatus(User user, String orderId, String status) {
		int setStat = Integer.valueOf(status);
		int id = Integer.valueOf(orderId);
		if(setStat != 1 && setStat != 2){
			return;
		}
		Order order = get(Order.class, id);
		if(order.getUser().getId() != user.getId() || order.getStatus() > 0){
			return;
		}
		order.setStatus(setStat);
		update(order);
	}
}
