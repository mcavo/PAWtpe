package models;

import org.junit.Test;

import ar.edu.itba.it.paw.models.Calification;

public class CalificationTest {
	
	public CalificationTest() {
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void tooLongDescriptionTest() {
		new Calification(4, "descriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescription");
	}

	@Test(expected = IllegalArgumentException.class)
	public void invalidStartMinTest() {
		new Calification(-1,
				"description");
	}

	@Test(expected = IllegalArgumentException.class)
	public void tooLongEmailTest() {
		new Calification(6,
				"description");
	}
	
	@Test
	public void okTest() {
		new Calification(
				3,
				"descrition"
				);
	}
}
