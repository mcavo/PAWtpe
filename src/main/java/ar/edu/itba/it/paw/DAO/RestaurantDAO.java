package ar.edu.itba.it.paw.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
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
			ResultSet sr = pstmt.executeQuery();
			
			if(!sr.next()) {
				sr.close();
				return null;
			}

			PreparedStatement pstmt2 = dbConnection.prepareStatement(sql);
			sql = "SELECT * FROM direccion WHERE id = ?;";
			pstmt2 = dbConnection.prepareStatement(sql);
			pstmt2.setInt(1, sr.getInt("dirid"));
			ResultSet sd = pstmt2.executeQuery();
			sd.next();

			Address address = new Address (sd.getString("calle"),sd.getInt("numero"), sd.getInt("piso"), sd.getString("departamento"), sd.getString("barrio"),sd.getString("localidad"),sd.getString("provincia")); 

			PreparedStatement pstmt3 = dbConnection.prepareStatement(sql);
			sql = "SELECT * FROM tipos WHERE restid = ?;";
			pstmt3 = dbConnection.prepareStatement(sql);
			pstmt3.setInt(1, id);
			ResultSet st = pstmt3.executeQuery();
			
			List<String> l = new ArrayList<String>();
			
			while(st.next()) {
				l.add(st.getString("tipo"));
			}
			st.close();
			Restaurant res = new Restaurant(sr.getString("name"), sr.getFloat("montomin"), sr.getFloat("desde"), sr.getFloat("hasta"), address, l, null, null);
		
			pstmt.close();
			pstmt2.close();
			pstmt3.close();
			sr.close();
			st.close();
			sd.close();
			return res;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<Restaurant> getLastWeekAdded() {
		List<Restaurant> rests = new LinkedList<Restaurant>();

		Connection dbConnection;
		DBManager db = DBManager.getInstance();
		dbConnection = db.getConnection();

		try {
			
			ResultSet sr = dbConnection.createStatement().executeQuery("SELECT * FROM restaurante WHERE CURRENT_DATE - DATE(regis) <= 7 ;");
			while(sr.next()) {
				String sql = "SELECT * FROM direccion WHERE id = ?;";
				PreparedStatement pstmt = dbConnection.prepareStatement(sql);
				pstmt.setInt(1, sr.getInt("dirid"));
				ResultSet sd = pstmt.executeQuery();
				sd.next();
				Address address = new Address (sd.getString("calle"),sd.getInt("numero"), sd.getInt("piso"), sd.getString("departamento"), sd.getString("barrio"),sd.getString("localidad"),sd.getString("provincia")); 
				sd.close();
				sql = "SELECT * FROM tipos WHERE restid = ?;";
				pstmt = dbConnection.prepareStatement(sql);
				pstmt.setInt(1, sr.getInt("id"));
				ResultSet st = pstmt.executeQuery();
				
				List<String> l = new ArrayList<String>();
				
				while(st.next()) {
					l.add(st.getString("tipo"));
				}
				st.close();
				rests.add(new Restaurant(sr.getString("nombre"), sr.getFloat("montomin"), sr.getFloat("desde"), sr.getFloat("hasta"), address, l, null, null));
			}
			sr.close();
			dbConnection.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public List<Restaurant> getAll() {
		Connection dbConnection;
		DBManager db = DBManager.getInstance();
		dbConnection = db.getConnection();
		List<Restaurant> rest = new LinkedList<Restaurant>();
		try {
			String sql = "SELECT * FROM restaurante";
			PreparedStatement pstmt = dbConnection.prepareStatement(sql);
			PreparedStatement pstmt2 = null;
			ResultSet sr = pstmt.executeQuery();
			while(sr.next()) {
				sql = "SELECT * FROM direccion WHERE id = ?;";
				pstmt = dbConnection.prepareStatement(sql);
				pstmt.setInt(1, sr.getInt("dirid"));
				ResultSet sd = pstmt.executeQuery();
				sd.next();
				
				Address address = new Address (sd.getString("calle"),sd.getInt("numero"), sd.getInt("piso"), sd.getString("departamento"), sd.getString("barrio"),sd.getString("localidad"),sd.getString("provincia")); 
				sd.close();

				pstmt2 = dbConnection.prepareStatement(sql);
				sql = "SELECT * FROM tipos WHERE restid = ?;";
				pstmt2 = dbConnection.prepareStatement(sql);
				pstmt2.setInt(1, sr.getInt("id"));
				ResultSet st = pstmt2.executeQuery();
				
				List<String> l = new ArrayList<String>();
				while(st.next()) {
					l.add(st.getString("tipo"));
				}
				st.close();
				rest.add(new Restaurant(sr.getString("nombre"), sr.getFloat("montomin"), sr.getFloat("desde"), sr.getFloat("hasta"), address, l, null, null));
			}
			pstmt.close();
			pstmt2.close();
			//dbConnection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rest;
		
	}
	
	private List<String> getTypesOfFoodByRestId(int restId){
		Connection dbConnection;
		DBManager db = DBManager.getInstance();
		dbConnection = db.getConnection();
		List<Restaurant> rest = new LinkedList<Restaurant>();
		
		List<String> tof = new LinkedList<String>();
		try {
			String sql = "SELECT tipo FROM tipos WHERE restid = ?";
			PreparedStatement pstmt = dbConnection.prepareStatement(sql);
			pstmt.setInt(1, restId);
			
			ResultSet rs = pstmt.executeQuery();
			while ( rs.next() ) {
			    tof.add(rs.getString("tipo"));
			 }
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tof;
	}
	
	private int getAddressId(String street, int number, String neighborhood, String city, String province, int floor, String apartment) {

		List<Restaurant> rest = new LinkedList<Restaurant>();
		int id = -1;
		try {
			Connection conn = DBManager.getInstance().getConnection();
			String sql = "SELECT id FROM direccion WHERE calle like ? and numero = ? and barrio like ? and localidad like ? and provincia like ? and (piso = ? or piso is null) and (departamento like ? or departamento is null);";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, street);
			pstmt.setInt(2, number);
			pstmt.setString(3, neighborhood);
			pstmt.setString(4, city);
			pstmt.setString(5, province);
			pstmt.setInt(6, floor);
			pstmt.setString(7, apartment);
			
			ResultSet rs = pstmt.executeQuery();
			while ( rs.next() ) {
			    id  = rs.getInt("id");
			 }
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	
	private Menu getMenuByRestId(int restId){
		/*-------------HARDCODEADO--------------*/
		LinkedList<Section> sections = new LinkedList<Section>(); 
		LinkedList<Dish> dishes = new LinkedList<Dish>();
		
		dishes.add(new Dish("milanesa", 50, "de carne")); 
		sections.add(new Section("Plato principal", dishes)); 
		Menu menu = null;
		try {
			menu = new Menu(sections);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		/*-------------------------------------*/
		return menu;
	}
	
	public Restaurant getRestaurant(String name, String street, int number, String neighborhood, String city, String province, int floor, String apartment) {
		int addressId = getAddressId(street, number, neighborhood, city, province, floor, apartment);
		if(addressId == -1){
			//app error!
			return null;
		}
		Address address = new Address(street, number, floor, apartment, neighborhood, city, province);
		Connection dbConnection;
		DBManager db = DBManager.getInstance();
		
		int restId = -1;
		Restaurant rest = null;

		try {
			dbConnection = db.getConnection();
			String sql = "SELECT * FROM restaurante WHERE dirid = ?;";
			PreparedStatement pstmt = dbConnection.prepareStatement(sql);
			pstmt.setInt(1, addressId);
			ResultSet rs = pstmt.executeQuery();
					 
			while ( rs.next() ) {
				restId = rs.getInt("id");
				rest = new Restaurant(rs.getString("nombre"), rs.getDouble("montomin"), rs.getFloat("desde"), rs.getFloat("hasta"), address, getTypesOfFoodByRestId(restId), null, getMenuByRestId(restId));
			 }
	         rs.close();
	         pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rest;
	}
}
