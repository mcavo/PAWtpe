package ar.edu.itba.it.paw.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "usuario")
public class User implements UserPermissions {
	
	@Transient
	private String email;
	
	@Column(name = "nombre")
	private String firstName;
	
	@Column(name = "apellido")
	private String lastName;
	
	@Column(name = "nacimiento")
	private Date birth;
	private Boolean isManager;
	private Boolean isAdmin;
	
	@ManyToOne
	@JoinColumn(name="dirid")
	private Address address;
	
	@Id
	@Column(name = "userid")
	private int id;
	
	public User(){}

	public User(String firstName, String lastName, Date birth) {
		// TODO Auto-generated constructor stub
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setBirth(birth);
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

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
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
		return isAdmin;
	}
	
	public void setIsAdmin(boolean isAdmin){
		this.isAdmin = isAdmin;
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
