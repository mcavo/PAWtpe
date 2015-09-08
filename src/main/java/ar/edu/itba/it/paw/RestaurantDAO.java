package ar.edu.itba.it.paw;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Time;
import java.util.LinkedList;
import java.util.List;

import ar.edu.itba.it.paw.models.Address;
import ar.edu.itba.it.paw.models.Menu;
import ar.edu.itba.it.paw.models.Restaurant;
import ar.edu.itba.it.paw.models.Section;
import ar.edu.itba.it.paw.models.User;
import oracle.sql.DATE;

public class RestaurantDAO {

	private static RestaurantDAO instance = null;
	
	private Connection conn;
		
		protected RestaurantDAO(){
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			}catch(ClassNotFoundException ex) {
				System.out.println("Error: unable to load driver class!");
				System.exit(1);
			}catch(IllegalAccessException ex) {
				   System.out.println("Error: access problem while loading!");
				   System.exit(2);
			}catch(InstantiationException ex) {
				   System.out.println("Error: unable to instantiate driver!");
				   System.exit(3);
			}
		}
		
		public static RestaurantDAO getInstance(){
			if(instance == null){
				instance = new RestaurantDAO();
			}
			return instance;
		}
		
		public void openConnection(){
			/*try {
				Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			}catch(ClassNotFoundException ex) {
				System.out.println("Error: unable to load driver class!");
				System.exit(1);
			}catch(IllegalAccessException ex) {
				   System.out.println("Error: access problem while loading!");
				   System.exit(2);
			}catch(InstantiationException ex) {
				   System.out.println("Error: unable to instantiate driver!");
				   System.exit(3);
			}
			*/
			String URL = "jdbc:postgresql://localhost/postgres";
			String USER = "julietasal-lari";
			String PASS = "123456";
			try {
				Connection conn = DriverManager.getConnection(URL, USER, PASS);
				conn.setAutoCommit(false);
				this.conn = conn;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public List<Restaurant> getLastWeekAdded(){
			List<Restaurant> rests = new LinkedList<Restaurant>();
			
			String name = "rest1";
			double minimumPurchase = 1;
			Time startService = Time.valueOf("17:00");
			Time endService = Time.valueOf("20:00");
			Address address = new Address("street", 1, "city", "province", 2, "apartment");
			LinkedList<String> typeOfFood = new LinkedList<String>(); typeOfFood.add("mejicana");
			LinkedList<User>managers = new LinkedList<User>(); managers.add(new User("mail", null, null, false, null, null));
			Menu menu = null;
			try {
				menu = new Menu(new LinkedList<Section>());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Restaurant r = new Restaurant(name, minimumPurchase, startService, endService, address, typeOfFood, managers, menu);
			rests.add(r);
			
			return rests;
		}
}
