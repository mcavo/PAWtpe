package ar.edu.itba.it.paw.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "barrio")
public class Neighborhood {
	@Id
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "nombre", unique = true)
	private String name;
	
	@ManyToMany(mappedBy="deliveryneigh")
	private Set<Restaurant> restaurant;

	// Only to use with javabean
	public Neighborhood() {
	}

	public Neighborhood(String name) {
		this.name = name;
	}
	
	public Neighborhood(Integer id) {
		this.id = id;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Neighborhood)) {
			return false;
		}
		Neighborhood other = (Neighborhood) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

}
