package models;

import org.junit.Test;

import ar.edu.itba.it.paw.domain.users.Credential;

public class CredentialTest {

	public CredentialTest() { }
	
	@Test(expected = IllegalArgumentException.class)
	public void invalidRolTest() {
		new Credential(1,
				"rol", "email@email.com");
	}

	@Test(expected = IllegalArgumentException.class)
	public void invalidFormatEmailTest() {
		new Credential(1,
				"usuario", "emailemail@email");
	}

	@Test(expected = IllegalArgumentException.class)
	public void tooLongEmailTest() {
		new Credential(1,
				"usuario", "emailemailemailemailemailemailemailemailemailemailemailemail@email.com");
	}

	@Test
	public void okUserTest() {
		new Credential(
				1,
				"usuario", 
				"emailemail@email.com"
				);
	}
	
	@Test
	public void okManagerTest() {
		new Credential(
				1,
				"manager", 
				"emailemail@email.com"
				);
	}
	
	@Test
	public void okAdminTest() {
		new Credential(
				1,
				"admin", 
				"emailemail@email.com"
				);
	}
}
