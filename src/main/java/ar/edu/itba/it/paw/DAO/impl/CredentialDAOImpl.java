package ar.edu.itba.it.paw.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.DAO.CredentialDAO;
import ar.edu.itba.it.paw.DAO.DBManager;
import ar.edu.itba.it.paw.models.Credential;

@Repository
public class CredentialDAOImpl implements CredentialDAO{
		
	public CredentialDAOImpl() {
		// TODO Auto-generated constructor stub
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
		/*Credential cred = null;
		Session session = HibernateConnection.getInstance().getSessionFactory().openSession();
		//Transaction tx = session.beginTransaction();
		
		//String hql = "FROM credencial WHERE mail like :mail and psw = :psw";
		//Query query = session.createQuery(hql);
		//query.setParameter("mail", email);
		//query.setParameter("psw", pwd);
		@SuppressWarnings("unchecked")
		List<Credential> credentials = session.createQuery("from credencial").list();
		cred = credentials.get(0);
		
		session.close();
		return cred;*/
		
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

	public List<Credential> getManagersAvailables() {
		Connection con = DBManager.getInstance().getConnection();
		List<Credential> creds = new ArrayList<Credential>();
		try {
			ResultSet rs = con.createStatement().executeQuery("SELECT * FROM credencial AS c WHERE NOT EXISTS (SELECT * FROM gerente AS g WHERE c.id = g.userid ) AND EXISTS (SELECT * FROM usuario AS s WHERE s.userid = c.id);");
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
