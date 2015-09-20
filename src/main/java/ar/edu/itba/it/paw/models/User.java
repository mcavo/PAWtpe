package ar.edu.itba.it.paw.models;

import java.time.LocalDate;

public class User implements UserPermissions {
	private String email;
	private String firstName;
	private String lastName;
	private LocalDate birth;
	private boolean isManager;
	private Address address;
	private int id;

	public User(String firstName, String lastName, LocalDate birth) {
		// TODO Auto-generated constructor stub
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setBirth(birth);
		//this.setAddress(address); //Dirección obligatoria o puede ser optativa? y el teléfono??
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

	public LocalDate getBirth() {
		return birth;
	}

	public void setBirth(LocalDate birth) {
		this.birth = birth;
	}

	public void setManager(boolean isManager) {
		this.isManager = isManager;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	public int getAge() { // testear esto
		/*LocalDate today = LocalDate.now();
		int answer = this.birth.getYear() - today.getYear();
		if (this.birth.getMonthValue() - today.getMonthValue() > 0) {
			return answer;
		} else if (this.birth.getDayOfMonth() - today.getDayOfMonth() < 0){
			return answer - 1;
		}
		return answer; 
		*/
		return 0;
	}
	
	/* User Permissions */
	public boolean getIsAdmin(){
		return false;
	}
	
	public boolean getIsManager(){
		return isManager;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
