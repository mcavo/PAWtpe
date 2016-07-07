package ar.edu.itba.it.paw.domain.users;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.domain.address.AddressRepository;
import ar.edu.itba.it.paw.domain.common.AbstractHibernateRepository;

@Repository
public class UserRepository extends AbstractHibernateRepository implements UserRepositoryType {

	private CredentialRepository credentialRepository;
	private AddressRepository addressRepository;
	
	@Autowired
	public UserRepository(SessionFactory sessionFactory, CredentialRepository credentialRepository, AddressRepository addressRepository){
		super(sessionFactory);
		this.credentialRepository = credentialRepository;
		this.addressRepository = addressRepository;
	}
	
	public User getUser(Credential cred){
		if(cred == null)
			return null;
		User user = getUserById(cred.getId());
		user.setEmail(cred.getMail());
		user.setManager(cred.getRol().equals("manager"));
		user.setIsAdmin(cred.getRol().equals("admin"));
		return user;
	}
	
	public User getUser(String email){
		Credential cred = credentialRepository.get(email);
		return getUser(cred);
	}
	
	public User getUserById(int id){
		return get(User.class, id);
	}
	
	public User setUser(User user, String pwd) {
		String role;
		role = user.getIsManager() ? "manager" : "usuario"; 
		int userid = -1;
		Credential credential = new Credential(role, user.getEmail(), pwd);
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
		role = user.getIsManager() ? "manager" : (user.getIsAdmin() ? "admin" : "usuario"); 
		
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
	
	public void setIfManager(User user, String rol) {
		user.setManager(rol.equals("manager"));
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

	public Question getQuestion(int userId) {
		List<Question> l = find("select u.question from User u where u.id = ?", userId);
		if(l.isEmpty())
			return null;
		return l.get(0);
	}

	public boolean existsUser(int userId) {
		return getUserById(userId) != null;
	}
}
