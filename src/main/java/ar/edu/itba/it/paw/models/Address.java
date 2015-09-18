package ar.edu.itba.it.paw.models;

public class Address {
	private String street;
	private String number;
	private String neighborhood;
	private String city;
	private String province;
	private String floor;
	private String apartment;
	
	public Address(String street, String number, String floor, String apartment, String neighborhood, String city, String province) {
		this.setStreet(street);
		this.setNumber(number);
		this.setNeighborhood(neighborhood);
		this.setCity(city);
		this.setProvince(province);
		this.setFloor(floor);
		this.setApartment(apartment);
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getApartment() {
		return apartment;
	}

	public void setApartment(String apartment) {
		this.apartment = apartment;
	}
	

	public String parseAdress() {
		String add = street + " " + number;
		if (floor!=null)
			add = add + floor + "º piso";
		if (apartment!=null)
			add = add + " " + apartment;
		return add + ", " + neighborhood +", "+city+", "+province+"." ;
	}
}
