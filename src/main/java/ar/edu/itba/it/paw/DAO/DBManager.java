package ar.edu.itba.it.paw.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {

	private static DBManager instance = null;
	private Connection conn;
	
	protected DBManager(){
	}
	
	public static DBManager getInstance(){
		if(instance == null){
			instance = new DBManager();
		}
		return instance;
	}
	
	public void openConnection(){

		String URL = "jdbc:postgresql://localhost:5432/postgres";
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
	
	public boolean closeConnection(){
		try {
			this.conn.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public Connection getConnection(){
		try {
			if(this.conn == null || this.conn.isClosed()){
				openConnection();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.conn;
	}

	
	public String getUserId(String mail, String pwd){
		return "5859";
	}
}
