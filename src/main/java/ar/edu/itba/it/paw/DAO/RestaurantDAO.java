package ar.edu.itba.it.paw.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
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

	protected RestaurantDAO() {

	}

	public static RestaurantDAO getInstance() {
		if (instance == null) {
			instance = new RestaurantDAO();
		}
		return instance;
	}

	public List<Restaurant> filterBy(String typeOfFood ) {
		Connection dbConnection;
		DBManager db = DBManager.getInstance();
		dbConnection = db.getConnection();
		List<Restaurant> rest = new LinkedList<Restaurant>();
		try {
			String sql = "SELECT DISTINCT id FROM tipos WHERE tipo = ?";
			PreparedStatement pstmt = dbConnection.prepareStatement(sql);
			pstmt.setString(1, typeOfFood);
			ResultSet s = pstmt.executeQuery();
			while(s.next()) {
				Restaurant aux = getById(s.getInt("id"));
				if(aux!=null)
					rest.add(aux);
			}
			dbConnection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return rest;
	}
	
	private Restaurant getById(int id) {
		Connection dbConnection;
		DBManager db = DBManager.getInstance();
		
		try {
			dbConnection = db.getConnection();
			String sql = "SELECT * FROM restaurante WHERE id = ?;";
			PreparedStatement pstmt = dbConnection.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet sr = pstmt.executeQuery(); // Me traje el restaurante
			
			if(!sr.next())
				return null;
			
			sql = "SELECT * FROM direccion WHERE id = ?;";
			pstmt = dbConnection.prepareStatement(sql);
			pstmt.setInt(1, sr.getInt("dirid"));
			ResultSet sd = pstmt.executeQuery();
			sd.next();
			
			Address address = new Address (sd.getString("calle"),sd.getString("numero"), sd.getString("piso"), sd.getString("departamento"), sd.getString("barrio"),sd.getString("localidad"),sd.getString("provincia")); 
			
			sql = "SELECT * FROM tipos WHERE restid = ?;";
			pstmt = dbConnection.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet st = pstmt.executeQuery();
			
			List<String> l = new ArrayList<String>();
			
			while(st.next()) {
				l.add(st.getString("tipo"));
			}
			
			Restaurant res = new Restaurant(sr.getString("name"), sr.getFloat("montomin"), sr.getFloat("desde"), sr.getFloat("hasta"), address, l, null, null);
		
			return res;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<Restaurant> getLastWeekAdded() {
		List<Restaurant> rests = new LinkedList<Restaurant>();

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
		
		java.sql.Timestamp sow = new java.sql.Timestamp(cal.getTimeInMillis());


		//ResultSet set = selectSQL("SELECT * FROM restaurante WHERE regis <= "+"\""+sow.toString()+"\"");
		/*
		 * String name = "Taco Box"; double minimumPurchase = 1; Time
		 * startService = Time.valueOf("17:00:00"); Time endService =
		 * Time.valueOf("20:00:00"); Address address = new Address("street",
		 * "1", "neigh", "city", "province", "2", "apartment");
		 * LinkedList<String> typeOfFood = new LinkedList<String>();
		 * typeOfFood.add("mejicana"); LinkedList<User>managers = new
		 * LinkedList<User>(); managers.add(new User("mail", null, null, false,
		 * null, null)); Menu menu = null; try { LinkedList<Section> sections =
		 * new LinkedList<Section>(); LinkedList<Dish> dishes = new
		 * LinkedList<Dish>();
		 * 
		 * dishes.add(new Dish("milanesa", 50, "de carne")); sections.add(new
		 * Section("Plato principal", dishes)); menu = new Menu(sections); }
		 * catch (Exception e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 * 
		 * Restaurant r = new Restaurant(name, minimumPurchase, startService,
		 * endService, address, typeOfFood, managers, menu); rests.add(r);
		 */
		return rests;
	}

	public Restaurant getRestaurant(String key) {
		double minimumPurchase = 1;
		Time startService = Time.valueOf("17:00:00");
		Time endService = Time.valueOf("20:00:00");
		Address address = new Address("street", "1", "neigh", "city", "province", "2", "apartment");
		LinkedList<String> typeOfFood = new LinkedList<String>();
		typeOfFood.add("mejicana");
		LinkedList<User> managers = new LinkedList<User>();
		managers.add(new User("mail", null, null, false, null, null));
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

		//Restaurant r = new Restaurant("nombre", minimumPurchase, startService, endService, address, typeOfFood, managers, menu);
		return null;
	}

	public List<Restaurant> getAll() {
		Connection dbConnection;
		DBManager db = DBManager.getInstance();
		dbConnection = db.getConnection();
		List<Restaurant> rest = new LinkedList<Restaurant>();
		try {
			String sql = "SELECT * FROM restaurante";
			PreparedStatement pstmt = dbConnection.prepareStatement(sql);
			ResultSet sr = pstmt.executeQuery();
			while(sr.next()) {
				sql = "SELECT * FROM direccion WHERE id = ?;";
				pstmt = dbConnection.prepareStatement(sql);
				pstmt.setInt(1, sr.getInt("dirid"));
				ResultSet sd = pstmt.executeQuery();
				sd.next();
				Address address = new Address (sd.getString("calle"),sd.getString("numero"), sd.getString("piso"), sd.getString("departamento"), sd.getString("barrio"),sd.getString("localidad"),sd.getString("provincia")); 
				
				sql = "SELECT * FROM tipos WHERE restid = ?;";
				pstmt = dbConnection.prepareStatement(sql);
				pstmt.setInt(1, sr.getInt("id"));
				ResultSet st = pstmt.executeQuery();
				
				List<String> l = new ArrayList<String>();
				
				while(st.next()) {
					l.add(st.getString("tipo"));
				}
				
				rest.add(new Restaurant(sr.getString("nombre"), sr.getFloat("montomin"), sr.getFloat("desde"), sr.getFloat("hasta"), address, l, null, null));
			}
			dbConnection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rest;
		
	}
}
