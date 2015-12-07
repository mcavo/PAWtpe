package ar.edu.itba.it.paw.forms;

import java.util.Date;

import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.domain.address.Address;
import ar.edu.itba.it.paw.domain.address.AddressRepository;
import ar.edu.itba.it.paw.domain.users.Question;
import ar.edu.itba.it.paw.domain.users.User;
import ar.edu.itba.it.paw.services.DateService;

@Component
public class EditProfileForm {

	String firstname;
	String lastname;
	String birthDay;
	String birthMonth;
	String birthYear;
	String email;
	String street;
	String number;
	String floor;
	String apartment;
	String neigh;
	String city;
	String prov;
	Question question;
	String answer;
	
	public EditProfileForm() {
	}
	
	public User build(AddressRepository addressRepo) {
		Date d = DateService.date(Integer.parseInt(birthYear),Integer.parseInt(birthMonth),Integer.parseInt(birthDay));
		User us = new User(firstname, lastname, d);
		us.setAddress(this.getAddress(addressRepo));
		us.setEmail(email);
		us.setIsAdmin(false);
		us.setManager(false);
		us.setQuestion(question);
		us.setAnswer(answer);
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

	public Address getAddress(AddressRepository addressRepo) {
		Integer n = null;
		System.out.println("floor: "+floor+";");
		if(number!=null && !number.equals(""))
			n = Integer.parseInt(number);
		Integer f = null;
		if(floor!=null && !floor.equals(""))
			f = Integer.parseInt(floor);
		return new Address(street, n, f, apartment, addressRepo.getneighById(Integer.parseInt(neigh)), city, prov);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
	
	public void setQuestion(Question question) {
		this.question = question;
	}
	
	public Question getQuestion() {
		return question;
	}
	
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	public String getAnswer() {
		return answer;
	}
}
