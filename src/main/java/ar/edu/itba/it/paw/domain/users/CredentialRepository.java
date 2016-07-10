package ar.edu.itba.it.paw.domain.users;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.domain.common.AbstractHibernateRepository;
import ar.edu.itba.it.paw.exceptions.CredentialNoMatchException;
import ar.edu.itba.it.paw.exceptions.DuplicateEmailException;
import ar.edu.itba.it.paw.exceptions.NoCredentialException;
import ar.edu.itba.it.paw.exceptions.NoManagersAvailableException;

@Repository
public class CredentialRepository extends AbstractHibernateRepository implements CredentialRepositoryType {
	
	@Autowired
	public CredentialRepository(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	public void add(Credential credential) throws Exception {
		if(existsMail(credential.getMail())) {
			throw new DuplicateEmailException(credential);
		}
		save(credential);
	}
	
	public Credential get(int id) {
		return get(Credential.class, id);
	}
	
	public Credential get(String email) {
		if(email == null) {
			return null;
		}
		List<Credential> creds = find("select cred from Credential cred where cred.mail = ?", email);
		if (creds.isEmpty()) {
			return null;
		}
		return creds.get(0);
	}
	
	public int getCredentialID(String email) throws NoCredentialException {
		Credential credential = credentialWithMail(email);
		return credential.getId();
	}
	
	public Credential getCredentials(String email, String pwd) throws CredentialNoMatchException {
		List<Credential> list = find("from Credential where mail = ? AND psw = ?", email, pwd);
		if (!list.isEmpty()) {
			return list.get(0);	
		}
		throw new CredentialNoMatchException(email, pwd);
	}

	public List<Credential> getManagersAvailables() throws NoManagersAvailableException {
		List<Credential> credentials = null;
		credentials = super.find("from Credential c where c.rol != 'manager' and exists (select u from User u where u.id = c.id)");
	    if (credentials == null) {
	    	throw new NoManagersAvailableException();
	    }
	    return credentials;
	}
	
	public void setRol(String rol, User user){
		Credential c = get(user.getId());
		c.setRol(rol);
		update(c);
	}
	
	public void setPwd(String pwd, int id){
		Credential c = get(id);
		c.updatePassword(pwd);
		update(c);
	}
	
	private boolean existsMail(String mail) {
		return !find("from Credential where mail = ?", mail).isEmpty();
	}
	
	private Credential credentialWithMail(String email) throws NoCredentialException {
		List<Credential> list = find("from Credential where mail = ?", email);
		if (!list.isEmpty()) {
			return list.get(0);	
		}
		throw new NoCredentialException(email);
	}

}
