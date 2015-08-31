package ar.edu.itba.it.paw;

public interface UserManager {

public boolean existsUser();
	
	public String getUser();
		
	public void setUser(String name, String sign);
	
	public void resetUser(String name);
}
