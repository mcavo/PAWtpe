package ar.edu.itba.it.paw.models;

import java.util.HashMap;
import java.util.LinkedList;

public class Menu {
	private HashMap<String, LinkedList<Dish>> dishes;
	
	public Menu(LinkedList<String> sections, LinkedList<LinkedList<Dish>> dishes) throws Exception {
		if (sections.size() != dishes.size()) {
			throw new Exception("You try to create a section without a dish");
		}
		
		this.dishes = new HashMap<String, LinkedList<Dish>>();
		
		for (int i = 0; i < sections.size() ; i++) {
			if (dishes.get(i).size() == 0) {
				throw new Exception("You try to create a section without a dish");
			}
			this.dishes.put(sections.get(i), dishes.get(i));
		}
	}

	public HashMap<String, LinkedList<Dish>> getDishes() {
		return dishes;
	}

	public void setDishes(HashMap<String, LinkedList<Dish>> dishes) {
		this.dishes = dishes;
	}
	
	public LinkedList<Dish> getSection(String section) {
		return this.dishes.get(section);
	}
	
	public void setSection(String section, LinkedList<Dish> dishes) {
		if(!dishes.isEmpty()) {
			this.dishes.put(section, dishes);
		}
	}
}
