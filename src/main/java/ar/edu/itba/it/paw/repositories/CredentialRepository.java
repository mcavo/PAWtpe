package ar.edu.itba.it.paw.repositories;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.exceptions.CredentialNoMatchException;
import ar.edu.itba.it.paw.exceptions.DuplicateEmailException;
import ar.edu.itba.it.paw.exceptions.NoCredentialException;
import ar.edu.itba.it.paw.models.Credential;

@Repository
public class CredentialRepository extends AbstractHibernateRepository {
	
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
	
	public String getEmail(int credentialId) throws NoCredentialException {
		Credential credential = credentialWithId(credentialId);
		return credential.getMail();
	}

	@SuppressWarnings("unchecked")
	public List<Credential> getManagersAvailables() {
		List<Credential> credentials = null;
		Session session=null;
	    try 
	    {
		    Session sessionSQL = super.getSession();
		    Transaction tx = sessionSQL.beginTransaction();
		    SQLQuery query = (SQLQuery) sessionSQL.createSQLQuery("SELECT * FROM credencial c WHERE NOT EXISTS "
		    														+ "(SELECT * FROM gerente g WHERE c.id = g.userid ) "
		    														+ "AND EXISTS (SELECT * FROM usuario s WHERE s.userid = c.id)"); 
		    query.addScalar("id", Hibernate.INTEGER);
		    query.addScalar("rol", Hibernate.STRING);
		    query.addScalar("mail", Hibernate.STRING);
		    credentials = query.setResultTransformer(Transformers.aliasToBean(Credential.class)).list();
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
	    return credentials;
	}
	
	public void setRol(String rol, int id){
		Credential c = get(id);
		c.setRol("rol");
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
	
	private Credential credentialWithId(int id) throws NoCredentialException {
		List<Credential> list = find("from Credential where id = ?", id);
		if (!list.isEmpty()) {
			return list.get(0);	
		}
		throw new NoCredentialException(id);
	}
}
