package ar.edu.itba.it.paw.domain.address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ar.edu.itba.it.paw.domain.common.PersistentEntity;
import ar.edu.itba.it.paw.services.StringService;

@Entity
@Table(name = "direccion")
public class Address extends PersistentEntity {
	
	@Column(name = "calle")
	private String street;
	
	@Column(name = "numero")
	private Integer number;
	
	@ManyToOne
	@JoinColumn(name="barrioid")
	private Neighborhood neighborhood;
	
	@Column(name = "localidad")
	private String city;
	
	@Column(name = "provincia")
	private String province;
	
	@Column(name = "piso", nullable=true)
	private Integer floor;
	
	@Column(name = "departamento", nullable=true)
	private String apartment;
	
	//Only to use with javabean
	Address() {
		
	}
	
	public Address(String street, Integer number, Integer floor, String apartment, Neighborhood neighborhood, String city, String province) {
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

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Neighborhood getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(Neighborhood neighborhood) {
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

	public Integer getFloor() {
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
