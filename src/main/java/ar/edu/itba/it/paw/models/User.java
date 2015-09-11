package ar.edu.itba.it.paw.models;

import java.time.LocalDate;

public class User implements UserPermissions {
	private String email;
	private String name;
	private LocalDate birth;
	private boolean isManager;
	private String phoneNumber;
	private Address address;
	private int id;

	public User(String email, String name, LocalDate birth, boolean isManager, String phoneNumber, Address address) {
		// TODO Auto-generated constructor stub
		this.setEmail(email);
		this.setName(name);
		this.setBirth(birth);
		this.setManager(isManager);
		this.setPhoneNumber(phoneNumber);
		this.setAddress(address); //Dirección obligatoria o puede ser optativa? y el teléfono??
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirth() {
		return birth;
	}

	public void setBirth(LocalDate birth) {
		this.birth = birth;
	}

	public void setManager(boolean isManager) {
		this.isManager = isManager;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	public int getAge() { // testear esto
		LocalDate today = LocalDate.now();
		int answer = this.birth.getYear() - today.getYear();
		if (this.birth.getMonthValue() - today.getMonthValue() > 0) {
			return answer;
		} else if (this.birth.getDayOfMonth() - today.getDayOfMonth() < 0){
			return answer - 1;
		}
		return answer; 
	}
	
	/* User Permissions */
	/*public boolean isAdmin() {
		return false;
	}*/
	
	public boolean getIsAdmin(){
		return false;
	}
	
	/*public boolean isManager() {
		return isManager;
	}*/
	
	public boolean getIsManager(){
		return isManager;
	}
}
