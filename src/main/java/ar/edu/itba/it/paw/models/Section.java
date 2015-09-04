package ar.edu.itba.it.paw.models;

import java.util.LinkedList;

public class Section {
	private LinkedList<Dish> dishes;
	private String name;
	
	public Section(String name, LinkedList<Dish> dishes) {
		this.dishes = dishes;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String setName(String name) {
		this.name = name;
		return name;
	}

	public LinkedList<Dish> getDishes() {
		return dishes;
	}

	public void setDishes(LinkedList<Dish> dishes) {
		this.dishes = dishes;
	}

	public Dish getDish(String name) {
		for (Dish dish : this.dishes) {
			if (dish.getProduct().equals(name)) {
				return dish;
			}
		}
		return null;
	}

	public void setDish(Dish dish) {
		if (this.dishes.contains(dish)) {
			this.dishes.remove(dish);
		}
		this.dishes.add(dish);
	}
}
