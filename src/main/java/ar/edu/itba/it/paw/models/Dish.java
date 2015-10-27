package ar.edu.itba.it.paw.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "plato")
public class Dish {
	
	@Column(name = "nombre")
	private String product;
	
	@Column(name = "precio")
	private float price;
//	private String sectionMenu;
	
	@Column(name = "descripcion")
	private String description;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "seccion")
	private String section;

	public Dish(){}
	
	public Dish(int id, String product, float price, String description) {
		this.setProduct(product);
		this.setPrice(price);
		this.setDescription(description);
		this.id = id;
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

	public void setId(int id){
		this.id = id;
	}
	
	public int getId() {
		return this.id;
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
		result = prime * result + id;
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
		if (!(obj instanceof Dish)) {
			return false;
		}
		Dish other = (Dish) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}
}
