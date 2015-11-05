package ar.edu.itba.it.paw.forms;

import java.util.Date;

import ar.edu.itba.it.paw.models.Address;
import ar.edu.itba.it.paw.models.Neighborhood;
import ar.edu.itba.it.paw.models.User;

public class SignupForm {

	private String firstname;
	private String lastname;
	private String birthDay;
	private String birthMonth;
	private String birthYear;
	private String email;
	private String pwd;
	private String pwd2;
	private String street;
	private String number;
	private String floor;
	private String apartment;
	private String neigh;
	private String city;
	private String prov;

	public SignupForm() {
	}

	@SuppressWarnings("deprecation")
	public User build() {
		User us = new User(firstname, lastname, new Date(Integer.parseInt(birthYear),Integer.parseInt(birthMonth),Integer.parseInt(birthDay)));
		us.setAddress(this.getAddress());
		us.setEmail(email);
		us.setIsAdmin(false);
		us.setManager(false);
		return us;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstame(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public String getBirthMonth() {
		return birthMonth;
	}

	public void setBirthMonth(String birthMonth) {
		this.birthMonth = birthMonth;
	}

	public String getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(String birthYear) {
		this.birthYear = birthYear;
	}

	public Address getAddress() {
		Integer n = null;
		System.out.println("floor: "+floor+";");
		if(number!=null && !number.equals(""))
			n = Integer.parseInt(number);
		Integer f = null;
		if(floor!=null && !floor.equals(""))
			f = Integer.parseInt(floor);
		return new Address(street, n, f, apartment, Integer.parseInt(neigh), city, prov);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getPwd() {
		return pwd;
	}
	public void setPwd2(String pwd) {
		this.pwd = pwd;
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

	public String getNeigh() {
		return neigh;
	}

	public void setNeigh(String neigh) {
		this.neigh = neigh;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProv() {
		return prov;
	}

	public void setProv(String prov) {
		this.prov = prov;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getPwd2() {
		return pwd;
	}
}