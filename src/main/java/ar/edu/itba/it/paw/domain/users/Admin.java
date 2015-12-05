package ar.edu.itba.it.paw.domain.users;

import ar.edu.itba.it.paw.services.StringService;

public class Admin {
	private String name;
	private String email;
	
	public Admin(String name, String email) {
		this.setName(name);
		this.setEmail(email);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		StringService.validateMaximumLength(name, 30);
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		StringService.validateMail(email);
		this.email = email;
	}

}
