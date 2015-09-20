package ar.edu.itba.it.paw;

import ar.edu.itba.it.paw.models.User;

public interface UserManager {

	public boolean existsUser();
	
	public User getUser();
		
	public void setUser(User name);
	
	public void resetUser(String name);

	public String getUserId();
}
