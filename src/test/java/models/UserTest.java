package models;

import java.util.Date;

import org.junit.Test;

import ar.edu.itba.it.paw.models.User;

public class UserTest {
	private User user;
	
	public UserTest() {
		this.user = new User("Test", "Test", new Date(System.currentTimeMillis()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void tooLongFirstNameTest() {
		new User("firstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstName",
				"lastName", new Date(System.currentTimeMillis()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void tooLongLastNameTest() {
		new User("firstName",
				"lastNamelastNamelastNamelastNamelastNamelastNamelastNamelastNamelastNamelastNamelastNamelastNamelastNamelastNamelastName", new Date(System.currentTimeMillis()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void invalidFormatEmailTest() {
		this.user.setEmail("emailemail@email");
	}

	@Test(expected = IllegalArgumentException.class)
	public void tooLongEmailTest() {
		this.user.setEmail("emailemailemailemailemailemailemailemailemailemailemailemail@email.com");
	}

	@Test(expected = IllegalArgumentException.class)
	public void tooLongAnswerTest() {
		this.user.setAnswer("aswerasweraswerasweraswerasweraswerasweraswerasweraswerasweraswerasweraswerasweraswerasweraswerasweraswer");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nullAddressTest() {
		this.user.setAddress(null);
	}
	
	@Test
	public void okTest() {
		new User(
				"frstName",
				"lastName", 
				new Date(System.currentTimeMillis()));
	}
}