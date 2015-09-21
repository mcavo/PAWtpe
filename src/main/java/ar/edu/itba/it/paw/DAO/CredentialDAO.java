package ar.edu.itba.it.paw.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.edu.itba.it.paw.models.Credential;

public class CredentialDAO {
	
	public static CredentialDAO instance;
	
	public CredentialDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public static CredentialDAO getInstance() {
		if (instance == null) {
			instance = new CredentialDAO();
		}
		return instance;
	}

	// TODO: cambiar la excepción por una personalizada que diga que el mail está en uso.
	public int setCredentials(String email, String psw, String role) throws Exception {
		if (this.validateEmail(email)) {
			throw new Exception();
		}
		Connection con = DBManager.getInstance().getConnection();
		String query = "INSERT INTO credencial (mail, psw, rol) VALUES (?, ?, ?);";
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, email);
			pstmt.setString(2, psw);
			pstmt.setString(3, role);
			
			pstmt.execute();
			pstmt.close();
		} catch (SQLException e) {
			// TODO: si falla hay que canselar todo y tirar el error correspondiente
			e.printStackTrace();
		}
		return this.getCredentialID(email);
	}
	
	public int getCredentialID(String email) {
		Connection con = DBManager.getInstance().getConnection();
		String query = "SELECT id FROM credencial WHERE mail = ?";
		int id = -1;
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				id = rs.getInt("id");
//				String a = rs.getString("id");
			}
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	
	public Credential getCredentials(String email, String pwd) {
		Connection con = DBManager.getInstance().getConnection();
		String query = "SELECT * FROM credencial WHERE mail like ? and psw = ?";
		Credential cred = null;
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, email);
			pstmt.setString(2, pwd);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				cred = new Credential(rs.getInt("id"), rs.getString("rol"),rs.getString("mail"));
			}
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cred;
	}
	
	public Credential getCredentialsByEmail(String email) {
		Connection con = DBManager.getInstance().getConnection();
		String query = "SELECT * FROM credencial WHERE mail like ?";
		Credential cred = null;
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				cred = new Credential(rs.getInt("id"), rs.getString("rol"),rs.getString("mail"));
			}
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cred;
	}
	
	// TODO: por ahí hay que modificarlo cuando se realicen las verificaciones, pero en escencia es lo mismo.
	private boolean validateEmail(String email) {
		return this.getCredentialID(email) != -1;
	}
	
	public String getEmail(int credentialId) {
		Connection con = DBManager.getInstance().getConnection();
		String query = "SELECT mail FROM credencial WHERE id = ?";
		String mail = null;
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, credentialId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				mail = rs.getString("mail");
			}
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mail;
	}
	
	public void setManager(int userId) throws Exception {
		Connection con = DBManager.getInstance().getConnection();
		String query = "UPDATE credencial SET rol = ? WHERE id = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, "manager");
			pstmt.setInt(2, userId);
			pstmt.execute();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("The manager coulden't be set");
		}
	}

	public List<Credential> getManagersAvailables() {
		Connection con = DBManager.getInstance().getConnection();
		List<Credential> creds = new ArrayList<Credential>();
		try {
			ResultSet rs = con.createStatement().executeQuery("SELECT mail, FROM credencial AS c WHERE NOT EXISTS (SELECT * FROM gerente AS g WHERE c.id = g.userid  );");
			while(rs.next()) {
				creds.add(new Credential(rs.getInt("id"), rs.getString("rol"),rs.getString("mail")));
			}
			return creds;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
