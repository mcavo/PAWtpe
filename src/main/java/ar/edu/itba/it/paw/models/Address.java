package ar.edu.itba.it.paw.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "direccion")
public class Address {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "calle")
	private String street;
	
	@Column(name = "numero")
	private int number;
	
	@Column(name = "barrio")
	private String neighborhood;
	
	@Column(name = "localidad")
	private String city;
	
	@Column(name = "provincia")
	private String province;
	
	@Column(name = "piso", nullable=true)
	private Integer floor;
	
	@Column(name = "departamento", nullable=true)
	private String apartment;
	
	//Only to use with javabean
	public Address() {
		
	}
	
	public Address(String street, int number, int floor, String apartment, String neighborhood, String city, String province) {
	//public Address(String street, int number, String city, String province, String neighborhood) {
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
		if(this.floor == null){
			return -1;
		}
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
