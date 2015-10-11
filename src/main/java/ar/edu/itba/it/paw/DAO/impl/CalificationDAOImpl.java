package ar.edu.itba.it.paw.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.DAO.CalificationDAO;
import ar.edu.itba.it.paw.DAO.DBManager;
import ar.edu.itba.it.paw.models.Calification;
import ar.edu.itba.it.paw.models.Restaurant;
import ar.edu.itba.it.paw.models.User;
import ar.edu.itba.it.paw.services.impl.UserServiceImpl;

@Repository
public class CalificationDAOImpl implements CalificationDAO{

	public CalificationDAOImpl() {

	}
	
	public void addCalification(int userId, int restId, int rate, String comment){

		try {
			Connection conn = DBManager.getInstance().getConnection();
			String sql = "insert into Calificacion (userid, restid, descripcion, puntaje) VALUES (?, ?, ?, ?);";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			pstmt.setInt(2, restId);
			pstmt.setString(3, comment);
			pstmt.setInt(4, rate);
			
			pstmt.execute();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public HashMap<Integer, Calification> getCalifications(Restaurant restaurant) {
		HashMap<Integer, Calification> qMap = new HashMap<Integer, Calification>();
		
		try {
			Connection conn = DBManager.getInstance().getConnection();
			String sql = "select * from calificacion where restid = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, restaurant.getId());

			ResultSet rs = pstmt.executeQuery();
			int userid;
			String descripcion;
			int puntaje;
			Calification q;
			while(rs.next()){
				userid = rs.getInt("userid");
				descripcion = rs.getString("descripcion");
				puntaje = rs.getInt("puntaje");
				q = new Calification(puntaje, descripcion);
				qMap.put(userid,q);
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return qMap;
	}
}
