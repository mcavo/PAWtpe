package ar.edu.itba.it.paw.Repositories;

import java.util.List;

import org.hibernate.SessionFactory;

import ar.edu.itba.it.paw.Exceptions.CredentialNoMatchException;
import ar.edu.itba.it.paw.Exceptions.DuplicateEmailException;
import ar.edu.itba.it.paw.Exceptions.NoCredentialException;
import ar.edu.itba.it.paw.models.Credential;

public class CredentialRepository extends AbstractHibernateRepository {

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
	
	public int getCredentialID(String email) throws NoCredentialException {
		Credential credential = credentialWithMail(email);
		return credential.getId();
	}
	
	public Credential getCredentials(String email, String pwd) throws CredentialNoMatchException {
		List<Credential> list = find("from Credential where mail = ? AND pwd = ?", email, pwd);
		if (!list.isEmpty()) {
			return list.get(0);	
		}
		throw new CredentialNoMatchException(email, pwd);
	}
	
	public String getEmail(int credentialId) throws NoCredentialException {
		Credential credential = credentialWithId(credentialId);
		return credential.getMail();
	}

	public List<Credential> getManagersAvailables() {
		return find("FROM credencial AS c WHERE NOT EXISTS (SELECT * FROM gerente AS g WHERE c.id = g.userid ) AND EXISTS (SELECT * FROM usuario AS s WHERE s.userid = c.id);");
	}
	
	private boolean existsMail(String mail) {
		return !find("from credencial where mail = ?", mail).isEmpty();
	}
	
	private Credential credentialWithMail(String email) throws NoCredentialException {
		List<Credential> list = find("from credencial where mail = ?", email);
		if (!list.isEmpty()) {
			return list.get(0);	
		}
		throw new NoCredentialException(email);
	}
	
	private Credential credentialWithId(int id) throws NoCredentialException {
		List<Credential> list = find("from credencial where id = ?", id);
		if (!list.isEmpty()) {
			return list.get(0);	
		}
		throw new NoCredentialException(id);
	}
}
