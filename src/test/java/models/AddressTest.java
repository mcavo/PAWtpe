package models;

import org.junit.Test;

import ar.edu.itba.it.paw.models.Address;

public class AddressTest {

	public AddressTest() { }

//	public Address(String street, Integer number, Integer floor, String apartment, Integer neighborhood, String city, String province)

	@Test(expected = IllegalArgumentException.class)
	public void tooLongStreetTest() {
		new Address("streetstreetstreetstreetstreetstreetstreetstreetstreetstreetstreet", 
				10, null, null, 1, "city", "province");
	}

	@Test(expected = IllegalArgumentException.class)
	public void tooLongProvinceTest() {
		new Address("street", 10, null, null, 1, "city",
				"provinceprovinceprovinceprovinceprovinceprovinceprovinceprovinceprovinceprovinceprovinceprovinceprovinceprovinceprovince");
	}

	@Test(expected = IllegalArgumentException.class)
	public void tooLongCityTest() {
		new Address("street", 10, null, null, 1, "cityityityityityityityityityityityityityityityityityityityityityityity",
				"province");
	}

	@Test(expected = IllegalArgumentException.class)
	public void invalidFloorTest() {
		new Address("street", 10, -4, null, 1, "city", "province");
	}

	@Test(expected = IllegalArgumentException.class)
	public void invalidAparmentTest() {
		new Address("street", 10, 0, "aa", 1, "city", "province");
	}
	
	@Test
	public void okTest() {
		new Address("street", 10, null, null, 1, "city", "province");
	}
	
	@Test
	public void okTestAparment() {
		new Address("street", 10, null, "b", 1, "city", "province");
	}
	
	@Test
	public void okTestFloor() {
		new Address("street", 10, 1, "b", 1, "city", "province");
	}
}
