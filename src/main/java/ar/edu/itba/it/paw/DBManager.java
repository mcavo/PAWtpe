package ar.edu.itba.it.paw;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBManager {

	private static DBManager instance = null;
	private static Connection sDbConnection;

	public synchronized Connection getConnection() {
		if (sDbConnection == null)
			initConnection();
		return sDbConnection;
	}

	private DBManager() {
	}

	public static DBManager getInstance() {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}

	private static void initConnection() {
		URI dbUri;
		try {
			dbUri = new URI(
					"postgres://hubyihgpaouvuy:p59ImIPv_9CmNlMxbU-Cyn6tHF@ec2-54-235-162-144.compute-1.amazonaws.com:5432/dajenobv1kl0ho");
			String username = dbUri.getUserInfo().split(":")[0];
			String password = dbUri.getUserInfo().split(":")[1];
			String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
			String dbDriver = "org.postgresql.Driver";
			Properties prop = new Properties();
			prop.setProperty("user", username);
			prop.setProperty("password", password);
			String ssloff = "?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";

			try {
				Class.forName(dbDriver);
				dbUrl += ssloff;
				sDbConnection = DriverManager.getConnection(dbUrl, prop);
				System.out.println("Got Connection");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (URISyntaxException e1) {
			System.out.println("No se pudo conectar con la base de datos");
			e1.printStackTrace();
		}
	}

	public boolean closeConnection() {
		try {
			DBManager.sDbConnection.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	
	public void updateSQL(String sql){
		Statement stmt = null;
		try{
			Connection conn = this.getConnection();
			stmt = conn.createStatement();
	        stmt.executeUpdate(sql);
	        conn.commit();
	        stmt.close();
		}catch(Exception ex){
			System.out.println("updateSQL error:" + ex.getMessage());
		}
	}

	public void insertSQL(String sql){
		updateSQL(sql);
	}
	
	public void deleteSQL(String sql){
		updateSQL(sql);
	}
	
	public ResultSet selectSQL(String sql){
		Statement stmt = null;
		ResultSet rs = null;
		try{
			Connection conn = this.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
	        conn.commit();
	        stmt.close();
		}catch(Exception ex){
			System.out.println("updateSQL error:" + ex.getMessage());
		}
		return rs;
	}
}
