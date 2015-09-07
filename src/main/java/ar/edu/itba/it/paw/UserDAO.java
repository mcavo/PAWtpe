package ar.edu.itba.it.paw;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import ar.edu.itba.it.paw.models.User;

public class UserDAO {

	private static UserDAO instance = null;
	
private Connection conn;
	
	protected UserDAO(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
		}catch(ClassNotFoundException ex) {
			System.out.println("Error: unable to load driver class!");
			System.exit(1);
		}catch(IllegalAccessException ex) {
			   System.out.println("Error: access problem while loading!");
			   System.exit(2);
		}catch(InstantiationException ex) {
			   System.out.println("Error: unable to instantiate driver!");
			   System.exit(3);
		}
	}
	
	public static UserDAO getInstance(){
		if(instance == null){
			instance = new UserDAO();
		}
		return instance;
	}
	
	public void openConnection(){
		/*try {
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
		}catch(ClassNotFoundException ex) {
			System.out.println("Error: unable to load driver class!");
			System.exit(1);
		}catch(IllegalAccessException ex) {
			   System.out.println("Error: access problem while loading!");
			   System.exit(2);
		}catch(InstantiationException ex) {
			   System.out.println("Error: unable to instantiate driver!");
			   System.exit(3);
		}
		*/
		String URL = "jdbc:postgresql://localhost/postgres";
		String USER = "julietasal-lari";
		String PASS = "123456";
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			conn.setAutoCommit(false);
			this.conn = conn;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static User getUser(String mail, String pwd){
		return new User(mail, null, null, false, null, null);
	}

	public String getUserId(String mail) {
		return "";
	}
	
}

