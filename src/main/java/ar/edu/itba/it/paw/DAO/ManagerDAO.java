package ar.edu.itba.it.paw.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ar.edu.itba.it.paw.models.Restaurant;
import ar.edu.itba.it.paw.models.User;

public class ManagerDAO {

	private static ManagerDAO instance = null;

	protected ManagerDAO() {

	}
	
	public static ManagerDAO getInstance(){
		if(instance == null){
			instance = new ManagerDAO();
		}
		return instance;
	}

	public Restaurant getRestByManagerId(int managerId) {
		Restaurant rest = null;
		String sql = "SELECT * FROM gerente WHERE userid = ?";
		try {
			Connection conn = DBManager.getInstance().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, managerId);

			ResultSet rs = pstmt.executeQuery();
			if ( rs.next() ) {
				rest = RestaurantDAO.getInstance().getById(rs.getInt("restid"));
			 }
	         rs.close();
	         pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rest;
	}

	public void addDish(int restId, String section, String dish, int price, String desc) {
		String sql = "insert into plato (restid, nombre, seccion, descripcion, precio) VALUES (?, ?, ?, ?, ?)";
		try {
			Connection conn = DBManager.getInstance().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, restId);
			pstmt.setString(2, dish);
			pstmt.setString(3, section);
			pstmt.setString(4, desc);
			pstmt.setInt(5, price);

			pstmt.execute();
	         pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public boolean existsSection(String section) {
		String sql = "SELECT * FROM plato WHERE seccion like ?";
		try {
			Connection conn = DBManager.getInstance().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, section);

			ResultSet rs = pstmt.executeQuery();
			if ( !rs.next() ) {
				rs.close();
		        pstmt.close();
		        return false;
			 }
	         rs.close();
	         pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	public Restaurant getRestaurant(int managerId) {
		Restaurant rest = null;
		String sql = "SELECT * FROM restaurante WHERE id = (select restid from gerente where gerente.userid = ?)";
		int restId = -1;
		try {
			Connection conn = DBManager.getInstance().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, managerId);

			ResultSet rs = pstmt.executeQuery();
			if ( rs.next() ) {
				restId = rs.getInt("id");
				rest = new Restaurant(restId, rs.getString("nombre"), rs.getFloat("montomin"), rs.getFloat("desde"), rs.getFloat("hasta"), null, RestaurantDAO.getInstance().getTypesOfFoodByRestId(restId), RestaurantDAO.getInstance().getMenuByRestId(restId), rs.getFloat("costoenvio"));
			 }
	         rs.close();
	         pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rest;
	}

	public boolean existsDish(String dish) {
		String sql = "SELECT * FROM plato WHERE nombre like ?";
		try {
			Connection conn = DBManager.getInstance().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dish);

			ResultSet rs = pstmt.executeQuery();
			if ( rs.next() ) {
				rs.close();
		        pstmt.close();
		        return true;
			 }
	         rs.close();
	         pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public void setManager(int userId, int restId) {
		String sql = "INSERT INTO gerente (userid, restid) VALUES (?, ?);";
		String sql2 = "UPDATE credencial SET rol = ? WHERE id = ?;";
				try {
					Connection conn = DBManager.getInstance().getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, userId);
					pstmt.setInt(2, restId);
					pstmt.execute();
			        pstmt.close();
			        
			        PreparedStatement pstmt2 = conn.prepareStatement(sql2);
					pstmt2.setString(1, "gerente");
					pstmt2.setInt(2, userId);
					pstmt2.execute();
					pstmt2.close();
			        
			        
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		
	}

}
