package ar.edu.itba.it.paw.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ar.edu.itba.it.paw.models.Restaurant;

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
}
