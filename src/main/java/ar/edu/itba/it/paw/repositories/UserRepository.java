package ar.edu.itba.it.paw.repositories;

import java.time.DateTimeException;
import java.util.Date;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.exceptions.NoCredentialException;
import ar.edu.itba.it.paw.models.Address;
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
		user.setManager(cred.getRol().equals("gerente"));
		user.setIsAdmin(cred.getRol().equals("admin"));
		return user;
	}
	
	public User getUserById(int id){
		return get(User.class, id);
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
		return get(User.class, userid).getId();
	}
	
	public User setUser(User user, String pwd) {
		String role;
		role = user.getIsManager() ? "manager" : "usuario"; 
		int userid = -1;
		//int userid = credentialDAO.setCredentials(user.getEmail(), pwd, role); //Excpetion use to give feedback to the user if the email is still used
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
		save(user.getAddress());
		save(user);
		
		return user;
	}
	
	private User createUser(String mail, String firstName, String lastName, Date birth) {
		return new User(firstName, lastName, birth);
	}

	public static void setIfManager(User user, String rol) {
		user.setManager(rol.equals("gerente"));
		user.setIsAdmin(rol.equals("admin"));

	}
}
