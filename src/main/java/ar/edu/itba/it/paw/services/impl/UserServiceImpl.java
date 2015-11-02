package ar.edu.itba.it.paw.services.impl;

import java.time.DateTimeException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.itba.it.paw.exceptions.CredentialNoMatchException;

import ar.edu.itba.it.paw.models.Address;
import ar.edu.itba.it.paw.models.Credential;
import ar.edu.itba.it.paw.models.User;
import ar.edu.itba.it.paw.repositories.CredentialRepository;
import ar.edu.itba.it.paw.repositories.UserRepository;
import ar.edu.itba.it.paw.services.UserService;

@Service
public class UserServiceImpl implements UserService{

	private UserRepository userRepository;
	private CredentialRepository credentialRepository;
			
	public UserServiceImpl(){}
	
	@Autowired
	public UserServiceImpl(UserRepository userRepo, CredentialRepository credentialRepository){
		this.userRepository = userRepo;
		this.credentialRepository = credentialRepository;
	}
	
	public Credential getUserCredentials(String mail, String pwd){
//		SessionFactory sf = HibernateConnection.getInstance().getSessionFactory();
//		CredentialRepository credentialRepository = new CredentialRepository(sf);
		try {
			return credentialRepository.getCredentials(mail, pwd);
		} catch (CredentialNoMatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
//		return credentialDAO.getCredentials(mail, pwd);
	}
	
	public User getUserById(Credential cred){
		User user = userRepository.getUserById(cred.getId());
		if(user == null){
			//debería utilizar el get básico
			//app error
			return null;
		}
		user.setManager(cred.getRol().equals("gerente"));
		user.setIsAdmin(cred.getRol().equals("admin"));
		return user;
	}

	@SuppressWarnings("deprecation")
	public User signUp(User usr,String pwd) {
		return userRepository.setUser(usr, pwd); //TODO: esta exceptción debería ser cambiada por una más personalizada.
	}

	private User createUser(String mail, String firstName, String lastName, Date birth) {
		return new User(firstName, lastName, birth);
	}

	public static void setIfManager(User user, String rol) {
		user.setManager(rol.equals("gerente"));
		user.setIsAdmin(rol.equals("admin"));
	}

	@Override
	public User get(Integer id) {
		User user = userRepository.getUserById(id);
		Credential credential = credentialRepository.get(id);
		if(user == null){
			//app error
			return null;
		}
		user.setManager(credential.getRol().equals("gerente"));
		user.setIsAdmin(credential.getRol().equals("admin"));
		return user;
	}
}
