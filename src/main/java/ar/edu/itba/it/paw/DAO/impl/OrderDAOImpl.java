package ar.edu.itba.it.paw.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.DAO.DBManager;
import ar.edu.itba.it.paw.DAO.OrderDAO;
import ar.edu.itba.it.paw.models.Dish;
import ar.edu.itba.it.paw.models.Order;
import ar.edu.itba.it.paw.models.Restaurant;

@Repository
public class OrderDAOImpl implements OrderDAO{
	
	private UserDAOImpl userDAO;
	
	public OrderDAOImpl() {

	}
	
	public OrderDAOImpl(UserDAOImpl userDao){
		this.userDAO = userDao;
	}
	

	public boolean checkDish(Restaurant rest, Dish dish) {
		String sql = "SELECT * FROM plato WHERE restid = ? and nombre like ? and descripcion like ? and precio = ?";
		boolean empty = true;
		try {
			Connection conn = DBManager.getInstance().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rest.getId());
			pstmt.setString(2, dish.getProduct());
			pstmt.setString(3, dish.getDescription());
			pstmt.setFloat(4, dish.getPrice());

			ResultSet rs = pstmt.executeQuery();
			if ( rs.next() ) {
				empty = false;
			 }
	         rs.close();
	         pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return !empty;
	}

	public void sendOrder(int usrId, int restId, HashMap<Dish, Integer> oMap) {
		String sql = "insert into pedido (restid, userid, horario, estado) VALUES (?, ?, ?, ?)";
		int orderId = 0;
		try {
			Connection conn = DBManager.getInstance().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, restId);
			pstmt.setInt(2, usrId);
			pstmt.setInt(4, 0);

			java.util.Date date = new java.util.Date(System.currentTimeMillis()); 
			java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime()); 
			pstmt.setTimestamp(3, timestamp);
			pstmt.execute();
			pstmt.close();
			try {
				orderId = getOrderId(usrId, restId, timestamp);
				loadProducts(orderId, oMap);
			} catch (Exception e) {
				// rollback!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private int getOrderId(int usrId, int restId, Timestamp timestamp) {
		int orderId = 0;
		try {
			Connection conn = DBManager.getInstance().getConnection();
			String sql = "select id from pedido where restid = ? and userid = ? and horario = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, restId);
			pstmt.setInt(2, usrId);
			pstmt.setTimestamp(3, timestamp);

			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				orderId = rs.getInt("id");
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderId;
	}

	private void loadProducts(int orderId, HashMap<Dish, Integer> oMap) {
		for (Entry<Dish, Integer> set : oMap.entrySet()) {
			loadByOne(orderId, set.getKey().getId(), set.getValue());
		}
	}

	private void loadByOne(int orderId, int dishId, int cant) {
		try {
			Connection conn = DBManager.getInstance().getConnection();
			String sql = "insert into prodPedidos (pedid, platoid, cant) VALUES (?, ?, ?);";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, orderId);
			pstmt.setInt(2, dishId);
			pstmt.setInt(3, cant);

			pstmt.execute();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Dish getDishByRestAndName(int restId, String nameProd) {
		Dish dish = null;
		try {
			Connection conn = DBManager.getInstance().getConnection();
			String sql = "select * from plato where restid = ? and nombre like ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, restId);
			pstmt.setString(2, nameProd);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				dish = new Dish(rs.getInt("id"), rs.getString("nombre"), rs.getFloat("precio"), rs.getString("descripcion"));
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dish;
	}

	public List<Order> getHistory(Restaurant rest) {
		List<Order> history = new LinkedList<Order>();
		Order order = null;
		try {
			Connection conn = DBManager.getInstance().getConnection();
			String sql = "select * from pedido where restid = ? ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rest.getId());

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				order = new Order(rest, userDAO.getUserById(rs.getInt("userid")), rs.getInt("estado"));
				order.setOrdlist(getOrderList(rs.getInt("id")));
				history.add(order);
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return history;
	}


	private Map<Dish, Integer> getOrderList(int orderId) {
		Map<Dish, Integer> order = new HashMap<Dish, Integer>();
		try {
			Connection conn = DBManager.getInstance().getConnection();
			String sql = "select * from prodPedidos where pedid = ? ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, orderId);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				order.put(getDishById(rs.getInt("platoid")), rs.getInt("cant"));
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return order;
	}

	private Dish getDishById(int id) {
		Dish dish = null;
		try {
			Connection conn = DBManager.getInstance().getConnection();
			String sql = "select * from plato where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				dish = new Dish(rs.getInt("id"), rs.getString("nombre"), rs.getFloat("precio"), rs.getString("descripcion"));
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dish;
	}
}