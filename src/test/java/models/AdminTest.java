package models;

import org.junit.Test;

import ar.edu.itba.it.paw.models.Admin;

public class AdminTest {

	public AdminTest() { }

	@Test(expected = IllegalArgumentException.class)
	public void tooLongFirstNameTest() {
		new Admin("firstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstName",
				"email@email.com");
	}

	@Test(expected = IllegalArgumentException.class)
	public void invalidFormatEmailTest() {
		new Admin("firstName",
				"email@email");
	}

	@Test(expected = IllegalArgumentException.class)
	public void tooLongEmailTest() {
		new Admin("firstName",
				"emailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemail@email");
	}
	
	@Test
	public void okTest() {
		new Admin(
				"frstName",
				"email@email.com"
				);
	}
	
}
