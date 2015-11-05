package ar.edu.itba.it.paw.repositories;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.exceptions.NoCredentialException;
import ar.edu.itba.it.paw.models.Credential;
import ar.edu.itba.it.paw.models.User;

@Repository
public class UserRepository extends AbstractHibernateRepository{

	private CredentialRepository credentialRepository;
	private AddressRepository addressRepository;
	
	@Autowired
	public UserRepository(SessionFactory sessionFactory, CredentialRepository credentialRepository, AddressRepository addressRepository){
		super(sessionFactory);
		this.credentialRepository = credentialRepository;
		this.addressRepository = addressRepository;
	}
	
	public User getUser(Credential cred){
		User user = getUserById(cred.getId());
		user.setEmail(cred.getMail());
		user.setManager(cred.getRol().equals("gerente"));
		user.setIsAdmin(cred.getRol().equals("admin"));
		return user;
	}
	
	public User getUserById(int id){
		return get(User.class, id);
	}
	
	public User setUser(User user, String pwd) {
		String role;
		role = user.getIsManager() ? "manager" : "usuario"; 
		int userid = -1;
		Credential credential = new Credential();
		credential.setMail(user.getEmail());
		credential.setRol(role);
		credential.setPsw(pwd);
		try { 
			credentialRepository.add(credential);
			userid = credentialRepository.getCredentialID(user.getEmail());
		} catch (Exception e) {
			return null;
		}
		user.setId(userid);
		saveUser(user);
		
		return user;
	}
	
	public User updateUser(User user) {
		String role;
		role = user.getIsManager() ? "manager" : "usuario"; 
		Credential credential = credentialRepository.get(user.getId());
		credential.setMail(user.getEmail());
		credential.setRol(role);
		try { 
			credentialRepository.update(credential);
			addressRepository.update(user.getAddress());
			update(user);
		} catch (Exception e) {
			return null;
		}
		return user;
	}
	
	@SuppressWarnings("unused")
	private User createUser(String mail, String firstName, String lastName, Date birth) {
		return new User(firstName, lastName, birth);
	}

	public static void setIfManager(User user, String rol) {
		user.setManager(rol.equals("gerente"));
		user.setIsAdmin(rol.equals("admin"));
	}
	
	public Serializable saveUser(User user) {
		addressRepository.saveAddress(user.getAddress());
		return save(user);
	}

	public boolean updatePassword(String userId, String newPwd, String respuesta) {
		int id = Integer.valueOf(userId);
		if(!find("from User where id = ? and respuesta = ?", id, respuesta).isEmpty()){
			credentialRepository.setPwd(newPwd, id);
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public String getQuestion(int userId) {
		List<String> preguntas = null;
		Session session=null;
	    try 
	    {
		    Session sessionSQL = super.getSession();
		    Transaction tx = sessionSQL.beginTransaction();
		    SQLQuery query = (SQLQuery) sessionSQL.createSQLQuery("SELECT pregunta FROM preguntas WHERE id = (select pregid from usuario where id = ?)").setParameter(0, userId); 
		    preguntas = query.list();
		    tx.commit();
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	    finally
	    {
	        if(session !=null && session.isOpen())
	        {
	          session.close();
	          session=null;
	        }
	    }
	    
	    if(preguntas == null || preguntas.isEmpty()){
	    	return "";
	    }
		return preguntas.get(0);
	}

	public boolean existsUser(int userId, String name, String lastName) {
		return !find("from User where id = ? and nombre = ? and apellido = ?", userId, name, lastName).isEmpty();
	}
}
