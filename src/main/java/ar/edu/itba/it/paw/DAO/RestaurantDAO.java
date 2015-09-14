package ar.edu.itba.it.paw.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Time;
import java.util.LinkedList;
import java.util.List;

import ar.edu.itba.it.paw.models.Address;
import ar.edu.itba.it.paw.models.Dish;
import ar.edu.itba.it.paw.models.Menu;
import ar.edu.itba.it.paw.models.Restaurant;
import ar.edu.itba.it.paw.models.Section;
import ar.edu.itba.it.paw.models.User;

public class RestaurantDAO {

	private static RestaurantDAO instance = null;
			
		protected RestaurantDAO(){

		}
		
		public static RestaurantDAO getInstance(){
			if(instance == null){
				instance = new RestaurantDAO();
			}
			return instance;
		}
		
		public List<Restaurant> getLastWeekAdded(){
			List<Restaurant> rests = new LinkedList<Restaurant>();
			
			String name = "Taco Box";
			double minimumPurchase = 1;
			Time startService = Time.valueOf("17:00:00");
			Time endService = Time.valueOf("20:00:00");
			Address address = new Address("street", 1, "city", "province", 2, "apartment");
			LinkedList<String> typeOfFood = new LinkedList<String>(); typeOfFood.add("mejicana");
			LinkedList<User>managers = new LinkedList<User>(); managers.add(new User("mail", null, null, false, null, null));
			Menu menu = null;
			try {
				LinkedList<Section> sections = new LinkedList<Section>();
				LinkedList<Dish> dishes = new LinkedList<Dish>();
				
				dishes.add(new Dish("milanesa", 50, "de carne"));
				sections.add(new Section("Plato principal", dishes));
				menu = new Menu(sections);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Restaurant r = new Restaurant(name, minimumPurchase, startService, endService, address, typeOfFood, managers, menu);
			rests.add(r);
			
			return rests;
		}

		public Restaurant getRestaurant(String key) {
			double minimumPurchase = 1;
			Time startService = Time.valueOf("17:00:00");
			Time endService = Time.valueOf("20:00:00");
			Address address = new Address("street", 1, "city", "province", 2, "apartment");
			LinkedList<String> typeOfFood = new LinkedList<String>(); typeOfFood.add("mejicana");
			LinkedList<User>managers = new LinkedList<User>(); managers.add(new User("mail", null, null, false, null, null));
			Menu menu = null;
			try {
				LinkedList<Section> sections = new LinkedList<Section>();
				LinkedList<Dish> dishes = new LinkedList<Dish>();
				
				dishes.add(new Dish("milanesa", 50, "de carne"));
				sections.add(new Section("Plato principal", dishes));
				menu = new Menu(sections);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Restaurant r = new Restaurant("nombre", minimumPurchase, startService, endService, address, typeOfFood, managers, menu);
			return r;
		}
}
