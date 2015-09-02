package ar.edu.itba.it.paw.models;

public class Admin {
	private String name;
	private String email;
//	private String phone;
	
	public Admin(String name, String email) {
		this.setName(name);
		this.setEmail(email);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
