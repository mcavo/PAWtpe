package ar.edu.itba.it.paw.domain.users;

import java.io.Serializable;

public interface UserRepositoryType {
	public User getUser(Credential cred);
	public User getUserById(int id);
	public User getUser(String email);
	public User setUser(User user, String pwd);
	public User updateUser(User user);
	public void setIfManager(User user, String rol);
	public Serializable saveUser(User user);
	public boolean updatePassword(String userId, String newPwd, String respuesta);
	public Question getQuestion(int userId);
	public boolean existsUser(int userId);
}
