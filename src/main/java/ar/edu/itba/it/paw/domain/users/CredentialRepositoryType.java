package ar.edu.itba.it.paw.domain.users;

import java.util.List;

import ar.edu.itba.it.paw.exceptions.CredentialNoMatchException;
import ar.edu.itba.it.paw.exceptions.NoCredentialException;
import ar.edu.itba.it.paw.exceptions.NoManagersAvailableException;

public interface CredentialRepositoryType {
	public void add(Credential credential) throws Exception;
	public Credential get(int id);
	public int getCredentialID(String email) throws NoCredentialException;
	public Credential getCredentials(String email, String pwd) throws CredentialNoMatchException;
	public List<Credential> getManagersAvailables() throws NoManagersAvailableException;
	public void setRol(String rol, User user);
	public void setPwd(String pwd, int id);
}
