package ar.edu.itba.it.paw.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import ar.edu.itba.it.paw.models.Address;
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
	
	public int getUserId(String mail, String pwd){
		String sql = "select id from credencial where mail like ? and psw like ?;";
		System.out.println(sql);
		int id = -1;
		try{
			Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mail);
			pstmt.setString(2, pwd);
			
			ResultSet rs = pstmt.executeQuery();
			while ( rs.next() ) {
			    id  = rs.getInt("id");
			 }
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	
	public User getUserById(int id){
		String sql = "select * from usuario where userid = ?";
		User user = null;
		try {
			Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while ( rs.next() ) {
			    String nombre = rs.getString("nombre");
			    String apellido = rs.getString("apellido");
			    LocalDate nacimiento = rs.getDate("nacimiento").toLocalDate();
			    int dirid  = rs.getInt("dirid");
			    int userid = rs.getInt("userid");
			    
			    String mail = CredentialDAO.getInstance().getEmail(userid);
			    Address address = AddressDAO.getInstance().getAddress(dirid);
			    user = new User(mail, nombre, apellido, nacimiento, false, address);
			 }
	         rs.close();
	         pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}

	public int getUserId(String mail) {
		int userid = CredentialDAO.getInstance().getCredentialID(mail);
		String query = "SELECT * FROM usuario WHERE id = ?";
		int userId = -1;
		DBManager.getInstance();
		Connection con = DBManager.getConnection();
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

	public User getUser(String id) {
		return new User("mail", null, null, null, false, null);
	}

	public Address getUserAddressById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getUserMailById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public User setUser(User user, String pwd) throws Exception {
		String role;
		role = user.getIsManager() ? "manager" : "usuario"; 
		int userid = CredentialDAO.getInstance().setCredentials(user.getEmail(), pwd, role); //Excpetion use to give feedback to the user if the email is still used
		int addressid = AddressDAO.getInstance().setAddress(user.getAddress());
	
		String query = "INSERT INTO usuario (userid, nombre, apellido, nacimiento, dirid ) VALUES (? , ?, ?, ?, ?);";
		DBManager.getInstance();
		Connection con = DBManager.getConnection();
		
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, userid);
			pstmt.setString(2, user.getFirstName());
			pstmt.setString(3, user.getLastName());
			System.out.println(user.getBirth());
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