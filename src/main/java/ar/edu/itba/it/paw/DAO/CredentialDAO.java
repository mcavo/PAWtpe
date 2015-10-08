package ar.edu.itba.it.paw.DAO;

public interface CredentialDAO {

	public int setCredentials(String email, String psw, String role) throws Exception;
	
	public int getCredentialID(String email);
}
