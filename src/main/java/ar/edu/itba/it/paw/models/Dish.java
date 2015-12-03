package ar.edu.itba.it.paw.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "plato")
public class Dish extends PersistentEntity {
	
	@Column(name = "nombre")
	private String product;
	
	@Column(name = "precio")
	private float price;
	
	@Column(name = "descripcion")
	private String description;
	
	@Column(name = "seccion")
	private String section;

	@ManyToOne
	@JoinColumn(name="restid")
	private Restaurant rest;

	public Dish(){}
	
	public Dish(int id, String product, float price, String description) {
		this.setProduct(product);
		this.setPrice(price);
		this.setDescription(description);
		this.setId(id);
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.getId();
		return result;
	}

	public Restaurant getRest() {
		return rest;
	}

	public void setRest(Restaurant rest) {
		this.rest = rest;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Dish)) {
			return false;
		}
		Dish other = (Dish) obj;
		if (this.getId() != other.getId()) {
			return false;
		}
		return true;
	}
}
