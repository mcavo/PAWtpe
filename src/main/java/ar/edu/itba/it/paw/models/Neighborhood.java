package ar.edu.itba.it.paw.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "barrio")
public class Neighborhood {

	@Id
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "nombre", unique = true)
	private String name;

	// Only to use with javabean
	public Neighborhood() {
	}

	public Neighborhood(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
