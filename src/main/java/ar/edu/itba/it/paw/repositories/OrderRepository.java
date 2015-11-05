package ar.edu.itba.it.paw.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.models.Dish;
import ar.edu.itba.it.paw.models.Order;
import ar.edu.itba.it.paw.models.Restaurant;
import ar.edu.itba.it.paw.models.User;

@Repository
public class OrderRepository extends AbstractHibernateRepository{

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
	
	public int sendOrder(User user, Restaurant rest, HashMap<Dish, Integer> oMap) {
		if(!checkOrder(user.getId(), rest, oMap)){
			//show excp
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
		
		/*String sql = "insert into pedido (restid, userid, horario, estado) VALUES (?, ?, ?, ?)";
		int orderId = 0;
		try {
			Connection conn = DBManager.getInstance().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rest.getId());
			pstmt.setInt(2, usrId);
			pstmt.setInt(4, 0);

			java.util.Date date = new java.util.Date(System.currentTimeMillis()); 
			java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime()); 
			pstmt.setTimestamp(3, timestamp);
			pstmt.execute();
			pstmt.close();
			try {
				orderId = getOrderId(usrId, rest, timestamp);
				loadProducts(orderId, oMap);
			} catch (Exception e) {
				// rollback!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}
	
	private boolean checkOrder(int usrId, Restaurant rest, HashMap<Dish, Integer> oMap){
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
		for (Entry<Dish, Integer> set: oMap.entrySet()) {
			
			int cant; 
			Dish dish = set.getKey();
			//try {
				cant = set.getValue();
			/*} catch (java.lang.NumberFormatException e) {
				return false;
			}*/
			
			if(cant < 0){
				//cant excepcion
				return false;
			}
			if(!checkDish(rest, dish)){
				//dish no existe excp
				return false;
			}
			total += dish.getPrice()*cant;
		}
		if(total < rest.getMinamount()){
			//monto minimo no llegado
			return false;
		}
		return true;
	}
	
	private void loadProducts(int orderId, HashMap<Dish, Integer> oMap) {
		for (Entry<Dish, Integer> set : oMap.entrySet()) {
			loadByOne(orderId, set.getKey().getId(), set.getValue());
		}
	}

	private void loadByOne(int orderId, int dishId, int cant) {
		Session session=null;
	    try 
	    {
		    Session sessionSQL = super.getSession();
		    Transaction tx = sessionSQL.beginTransaction();
		    SQLQuery query = (SQLQuery) sessionSQL.createSQLQuery("insert into prodPedidos (pedid, platoid, cant) VALUES (?, ?, ?)");
		    query.setParameter(0, orderId); 
		    query.setParameter(1, dishId);
		    query.setParameter(2, cant);
		    query.executeUpdate();
		    tx.commit();
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
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
	
	public List<Order> getHistory(Restaurant rest) {
		List<Order> history = find("from Order where restid = ?", rest.getId());
		for (Order order : history) {
			order.setOrdlist(getOrderList(order.getId()));
		}
		return history;
	}


	@SuppressWarnings("unchecked")
	private Map<Dish, Integer> getOrderList(int orderId) {
		Map<Dish, Integer> order = new HashMap<Dish, Integer>();
		
		Session session=null;
	    try 
	    {
		    Session sessionSQL = super.getSession();
		    Transaction tx = sessionSQL.beginTransaction();
		    SQLQuery query = (SQLQuery) sessionSQL.createSQLQuery("select * from prodPedidos where pedid = ?").setParameter(0, orderId); 
		    List<Object[]> rows = query.list();
		    tx.commit();
		    
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
	    	e.printStackTrace();
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
