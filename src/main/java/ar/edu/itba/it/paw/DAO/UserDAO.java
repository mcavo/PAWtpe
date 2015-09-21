package ar.edu.itba.it.paw.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import ar.edu.itba.it.paw.models.Address;
import ar.edu.itba.it.paw.models.Credential;
import ar.edu.itba.it.paw.models.User;

public class UserDAO {

	private static UserDAO instance = null;
	
	protected UserDAO(){
		
	}
	
	public static UserDAO getInstance(){
		if(instance == null){
			instance = new UserDAO();
		}
		return instance;
	}
	
	public User getUserById(int id){
		String sql = "SELECT * FROM usuario WHERE userid = ?";
		boolean empty = true;
		String  nombre = "";
		String  apellido = "";
		Date nacimiento = null;
		int dirid = -1;
		try {
			Connection conn = DBManager.getInstance().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if ( rs.next() ) {
				empty = false;
			    nombre = rs.getString("nombre");
			    apellido = rs.getString("apellido");
			    nacimiento = rs.getDate("nacimiento");
			    dirid = rs.getInt("dirid");
			 }
	         rs.close();
	         pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(empty){
			return null;
		}
		User user = new User(id, nombre, apellido, nacimiento.toLocalDate());
		user.setAddress(AddressDAO.getInstance().getAddressById(dirid));
		return user;
	}

	public int getUserId(String mail) {
		int userid = CredentialDAO.getInstance().getCredentialID(mail);
		String query = "SELECT * FROM usuario WHERE id = ?";
		int userId = -1;
		DBManager.getInstance();
		Connection con = DBManager.getInstance().getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, userid);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				userId = rs.getInt("userid");
			}
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userId;
	}
	
	public User setUser(User user, String pwd) throws Exception {
		String role;
		role = user.getIsManager() ? "manager" : "usuario"; 
		int userid = CredentialDAO.getInstance().setCredentials(user.getEmail(), pwd, role); //Excpetion use to give feedback to the user if the email is still used
		int addressid = AddressDAO.getInstance().setAddress(user.getAddress());
	
		String query = "INSERT INTO usuario (userid, nombre, apellido, nacimiento, dirid ) VALUES (? , ?, ?, ?, ?);";
		DBManager.getInstance();
		Connection con = DBManager.getInstance().getConnection();
		
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, userid);
			pstmt.setString(2, user.getFirstName());
			pstmt.setString(3, user.getLastName());
			pstmt.setDate(4, Date.valueOf(user.getBirth()));
			pstmt.setInt(5, addressid); 
			
			pstmt.execute();
	        pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user.setId(userid);
		return user;
	}
	
}