package ar.edu.itba.it.paw.DAO.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.HibernateConnection;
import ar.edu.itba.it.paw.DAO.CredentialDAO;
import ar.edu.itba.it.paw.DAO.DBManager;
import ar.edu.itba.it.paw.DAO.UserDAO;
import ar.edu.itba.it.paw.Exceptions.NoCredentialException;
import ar.edu.itba.it.paw.Repositories.CredentialRepository;
import ar.edu.itba.it.paw.models.Credential;
import ar.edu.itba.it.paw.models.User;

@Repository
public class UserDAOImpl implements UserDAO{
	
	private CredentialDAO credentialDAO;
	private CredentialRepository credentialRepository;
	
	public UserDAOImpl(){
		SessionFactory sf = HibernateConnection.getInstance().getSessionFactory();
		this.credentialRepository = new CredentialRepository(sf);
	}
	
	@Autowired
	public UserDAOImpl(CredentialDAO dao){
		this.credentialDAO = dao;
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
		User user = new User(nombre, apellido, nacimiento.toLocalDate());
		user.setId(id);
		user.setAddress(new AddressDAOImpl().getAddressById(dirid));
		return user;
	}

	public int getUserId(String mail) {
		//int userid = credentialDAO.getCredentialID(mail);
		int userid;
		try {
			userid = credentialRepository.getCredentialID(mail);
		} catch (NoCredentialException e1) {
			// TODO Auto-generated catch block
			userid = -1;
		}
		String query = "SELECT * FROM usuario WHERE id = ?";
		int userId = -1;
		DBManager.getInstance();
		Connection con = DBManager.getInstance().getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, userid);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
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
		int userid = -1;
		//int userid = credentialDAO.setCredentials(user.getEmail(), pwd, role); //Excpetion use to give feedback to the user if the email is still used
		Credential credential = new Credential();
		credential.setMail(user.getEmail());
		credential.setRol(role);
		credentialRepository.add(credential);
		userid = credentialRepository.getCredentialID(user.getEmail());
		int addressid = new AddressDAOImpl().setAddress(user.getAddress());
	
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