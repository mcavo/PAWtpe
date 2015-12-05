package ar.edu.itba.it.paw.domain.users;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import ar.edu.itba.it.paw.domain.address.Address;
import ar.edu.itba.it.paw.services.StringService;

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
	
	@Column(name = "pregid")
	private Integer question;
	
	@Column(name="respuesta")
	private String answer;

	public User(){}

	public User(String firstName, String lastName, Date birth) {
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
		StringService.validateMail(email);
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
		if (address == null) {
			throw new IllegalArgumentException();
		}
		this.address = address;
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
		StringService.validateMaximumLength(firstName, 30);
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		StringService.validateMaximumLength(lastName, 30);
		this.lastName = lastName;
	}
	
	public Integer getQuestion() {
		return question;
	}

	public void setQuestion(Integer question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		StringService.validateMaximumLength(answer, 100);
		this.answer = answer;
	}
}
