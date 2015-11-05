package ar.edu.itba.it.paw.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import ar.edu.itba.it.paw.services.StringService;

@Entity
@Table(name = "direccion")
public class Address {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "calle")
	private String street;
	
	@Column(name = "numero")
	private Integer number;
	
	@Column(name="barrioid")
	private Integer neighborhood;
	
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
	
	public Address(String street, Integer number, Integer floor, String apartment, Integer neighborhood, String city, String province) {
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
		StringService.validateMaximumLength(street, 30);
		this.street = street;
	}

	public int getNumber() {
		if(this.number == null){
			return -1;
		}
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(Integer neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		StringService.validateMaximumLength(city, 30);
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		StringService.validateMaximumLength(province, 30);
		this.province = province;
	}

	public int getFloor() {
		if(this.floor == null){
			return -1;
		}
		return floor;
	}

	public void setFloor(Integer floor) {
		if (floor != null) {
			validateFloor(floor);	
		}
		this.floor = floor;
	}

	public String getApartment() {
		return apartment;
	}

	public void setApartment(String apartment) {
		if (apartment != null) {
			validateApartment(apartment);	
		}
		this.apartment = apartment;
	}

	public int getId() {
		if(this.id == null){
			return -1;
		}
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public static void validateFloor(int i) {
		if (i < -1) {
			throw new IllegalArgumentException("Invalid floor");
		}
	}
	
	public static void validateApartment(String str) {
		if(str.length() > 1) {
			throw new IllegalArgumentException("Invalid apartment");
		}
	}
}
