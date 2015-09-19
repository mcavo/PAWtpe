package ar.edu.itba.it.paw.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import ar.edu.itba.it.paw.models.Address;
import ar.edu.itba.it.paw.models.User;

public class UserDAO {

	private static UserDAO instance = null;
	private static DBManager DBinstance = null;
	
	protected UserDAO(){
		
	}
	
	public static UserDAO getInstance(){
		if(instance == null){
			instance = new UserDAO();
			DBinstance = DBManager.getInstance();
		}
		return instance;
	}
	
	public static int getUserId(String mail, String pwd){
		String sql = "SELECT id FROM credencial WHERE mail = ? AND psw = ?;";
		System.out.println(sql);
		int id = -1;
		try{
			Connection conn = DBinstance.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mail);
			pstmt.setString(2, pwd);
			
			ResultSet rs = pstmt.executeQuery();
			if ( rs.next() ) {
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
	
	public static User getUserById(int id){
		String sql = "SELECT * FROM usuario WHERE userid = ?";
		boolean empty = true;
		String  nombre = "";
		String  apellido = "";
		Date nacimiento = null;
		int dirid = -1;
		try {
			Connection conn = DBinstance.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if ( rs.next() ) {
				empty = false;
			    nombre = rs.getString("nombre");
			    apellido = rs.getString("apellido");
			    nacimiento = rs.getDate("nacimiento");
			    //dirid  = rs.getInt("girid");
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
		return new User(nombre+" "+apellido, nacimiento);
	}

	public String getUserId(String mail) {
		return "";
	}

	public static User getUser(String id) {
		return new User("mail", null, null, false, null, null);
	}

	public Address getUserAddressById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getUserMailById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

