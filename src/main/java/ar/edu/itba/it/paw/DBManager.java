package ar.edu.itba.it.paw;

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
	
	public static DBManager getInstance(){
		if(instance == null){
			instance = new DBManager();
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
	
	/*public Connection getConn(){
		return this.conn;
	}*/
	
	public String selectDB(String sql){
		openConnection();
		Statement pstmt = null;
		String result = "";
		try {
		   pstmt = conn.createStatement();
		   ResultSet rs = pstmt.executeQuery(sql);
		   while(rs.next()){
			   result = rs.getString("Description");   
		   }
		}
		catch (SQLException e) {
		   result = e.getMessage();
		}
		finally {
		   try {
			pstmt.close();
			conn.close();
		   } catch (SQLException e) {
				e.printStackTrace();
		   }
		}
		return result;
	}
	
	public void toDB(String SQLStmt){
		PreparedStatement pstmt = null;
		try {
		   pstmt = conn.prepareStatement(SQLStmt);
		   pstmt.executeQuery();
		   //. . .
		}
		catch (SQLException e) {
		   //. . .
		}
		finally {
		   try {
			pstmt.close();
		   } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public boolean verifyUser(String mail, String pwd){
		return true;
	}
	
	public String getUserId(String mail, String pwd){
		return "5859";
	}
}
