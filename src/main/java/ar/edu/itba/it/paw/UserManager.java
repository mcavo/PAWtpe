package ar.edu.itba.it.paw;

import ar.edu.itba.it.paw.domain.users.User;

public interface UserManager {

	public boolean existsUser();
	
	public User getUser();
		
	public boolean setUser(User name);
	
	public void resetUser(String name);

	public String getUserId();
}
