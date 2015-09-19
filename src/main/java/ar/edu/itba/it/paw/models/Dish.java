package ar.edu.itba.it.paw.models;

public class Dish {
	private String product;
	private float price;
//	private String sectionMenu;
	private String description;
	private int id;
	
	public Dish(String product, float price, String description) {
		this.setProduct(product);
		this.setPrice(price);
		this.setDescription(description);
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
