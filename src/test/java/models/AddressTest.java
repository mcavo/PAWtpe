package models;

import org.junit.Test;

import ar.edu.itba.it.paw.domain.address.Address;

public class AddressTest {

	public AddressTest() { }

//	public Address(String street, Integer number, Integer floor, String apartment, Integer neighborhood, String city, String province)

	@Test(expected = IllegalArgumentException.class)
	public void tooLongStreetTest() {
		new Address("streetstreetstreetstreetstreetstreetstreetstreetstreetstreetstreet", 
				10, null, null, null, "city", "province");
	}

	@Test(expected = IllegalArgumentException.class)
	public void tooLongProvinceTest() {
		new Address("street", 10, null, null, null, "city",
				"provinceprovinceprovinceprovinceprovinceprovinceprovinceprovinceprovinceprovinceprovinceprovinceprovinceprovinceprovince");
	}

	@Test(expected = IllegalArgumentException.class)
	public void tooLongCityTest() {
		new Address("street", 10, null, null, null, "cityityityityityityityityityityityityityityityityityityityityityityity",
				"province");
	}

	@Test(expected = IllegalArgumentException.class)
	public void invalidFloorTest() {
		new Address("street", 10, -4, null, null, "city", "province");
	}

	@Test(expected = IllegalArgumentException.class)
	public void invalidAparmentTest() {
		new Address("street", 10, 0, "aa", null, "city", "province");
	}
	
	@Test
	public void okTest() {
		new Address("street", 10, null, null, null, "city", "province");
	}
	
	@Test
	public void okTestAparment() {
		new Address("street", 10, null, "b", null, "city", "province");
	}
	
	@Test
	public void okTestFloor() {
		new Address("street", 10, 1, "b", null, "city", "province");
	}
}
