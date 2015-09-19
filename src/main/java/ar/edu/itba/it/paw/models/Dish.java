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

}
