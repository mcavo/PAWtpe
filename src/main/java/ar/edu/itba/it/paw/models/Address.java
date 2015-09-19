package ar.edu.itba.it.paw.models;

public class Address {
	private int id;
	private String street;
	private int number;
	private String neighborhood;
	private String city;
	private String province;
	private int floor;
	private String apartment;

	public Address(String street, int number, String city, String province, String neighborhood) {
		this.setStreet(street);
		this.setNumber(number);
		this.setNeighborhood(neighborhood);
		this.setCity(city);
		this.setProvince(province);
		this.floor = -1;
		this.apartment = null;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
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

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
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
		if (floor > -1)
			add = add + floor + "º piso";
		if (apartment!=null)
			add = add + " " + apartment;
		return add + ", " + neighborhood +", "+city+", "+province+"." ;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
