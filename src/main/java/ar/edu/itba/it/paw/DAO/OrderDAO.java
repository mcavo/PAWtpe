package ar.edu.itba.it.paw.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import ar.edu.itba.it.paw.models.Calification;
import ar.edu.itba.it.paw.models.Dish;
import ar.edu.itba.it.paw.models.Restaurant;
import ar.edu.itba.it.paw.models.User;

public class OrderDAO {
	
private static OrderDAO instance = null;
	
	protected OrderDAO(){
		
	}
	
	public static OrderDAO getInstance(){
		if(instance == null){
			instance = new OrderDAO();
		}
		return instance;
	}

	public static boolean checkDish(Restaurant rest, Dish dish) {
		String sql = "SELECT * FROM plato WHERE restid = ? and nombre like ?;";
		//String sql = "SELECT * FROM plato WHERE restid = ? and nombre like ? and descripcion like ? and precio = ?";
		boolean empty = true;
		try {
			Connection conn = DBManager.getInstance().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rest.getId());
			pstmt.setString(2, dish.getProduct());
			//pstmt.setString(3, dish.getDescription());
			//pstmt.setFloat(4, dish.getPrice());

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
}
