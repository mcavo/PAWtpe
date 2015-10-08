package ar.edu.itba.it.paw.DAO;

import ar.edu.itba.it.paw.models.User;

public interface UserDAO {

	public User getUserById(int id);
	
	public int getUserId(String mail);
	
	public User setUser(User user, String pwd) throws Exception;
	
}
