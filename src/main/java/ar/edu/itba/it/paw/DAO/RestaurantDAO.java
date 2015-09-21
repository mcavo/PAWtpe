package ar.edu.itba.it.paw.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import ar.edu.itba.it.paw.models.Address;
import ar.edu.itba.it.paw.models.Calification;
import ar.edu.itba.it.paw.models.Dish;
import ar.edu.itba.it.paw.models.Menu;
import ar.edu.itba.it.paw.models.Restaurant;
import ar.edu.itba.it.paw.models.Section;
import ar.edu.itba.it.paw.models.User;
import ar.edu.itba.it.paw.services.CalificationService;

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
			String sql = "SELECT DISTINCT restid FROM tipos WHERE tipo = ?";
			PreparedStatement pstmt = dbConnection.prepareStatement(sql);
			pstmt.setString(1, typeOfFood);
			ResultSet s = pstmt.executeQuery();
			while(s.next()) {
				Restaurant aux = getById(s.getInt("restid"));
				if(aux!=null)
					rest.add(aux);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return rest;
	}
	
	protected Restaurant getById(int id) {
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
				pstmt.close();
				return null;
			}

			int dirId = sr.getInt("dirid");
			Menu menu = getMenuByRestId(id);
			Address address = AddressDAO.getInstance().getAddressById(dirId);
			List<String> tipos = getTypesOfFoodByRestId(id);
			Restaurant res = new Restaurant(id, sr.getString("nombre"), sr.getFloat("montomin"), sr.getFloat("desde"), sr.getFloat("hasta"), address, tipos, menu);		
			sr.close();
			pstmt.close();
			return res;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<Restaurant> getLastWeekAdded() {
		List<Restaurant> rests = new LinkedList<Restaurant>();

		int restId = -1;
		Menu menu = null;
		try {
			Connection dbConnection;
			DBManager db = DBManager.getInstance();
			dbConnection = db.getConnection();
			ResultSet sr = dbConnection.createStatement().executeQuery("SELECT * FROM restaurante WHERE CURRENT_DATE - DATE(regis) <= 7 ;");
			while(sr.next()) {
				restId = sr.getInt("id");
				menu = getMenuByRestId(restId);
				Address address = AddressDAO.getInstance().getAddressById(sr.getInt("dirid"));
				List<String> tipos = getTypesOfFoodByRestId(restId);
				rests.add(new Restaurant(restId, sr.getString("nombre"), sr.getFloat("montomin"), sr.getFloat("desde"), sr.getFloat("hasta"), address, tipos, menu));
			}
			sr.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rests;
	}

	public List<Restaurant> getAll() {
		Connection dbConnection;
		DBManager db = DBManager.getInstance();
		dbConnection = db.getConnection();
		List<Restaurant> rest = new LinkedList<Restaurant>();
		
		int restId = -1;
		Menu menu = null;
		try {
			String sql = "SELECT * FROM restaurante";
			PreparedStatement pstmt = dbConnection.prepareStatement(sql);
			ResultSet sr = pstmt.executeQuery();
			while(sr.next()) {
				restId = sr.getInt("id");
				menu = getMenuByRestId(restId);
				Address address = AddressDAO.getInstance().getAddressById(sr.getInt("dirid"));
				List<String> tipos = getTypesOfFoodByRestId(restId);
				rest.add(new Restaurant(restId, sr.getString("nombre"), sr.getFloat("montomin"), sr.getFloat("desde"), sr.getFloat("hasta"), address, tipos, menu));
			}
			sr.close();
			pstmt.close();
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
	
	private Menu getMenuByRestId(int restId){
		Menu menu = null;
		LinkedList<Section> sections = new LinkedList<Section>();
		Dish dish = null;
		Section section = null;
		String seccion = null;
		try {
			Connection conn = DBManager.getInstance().getConnection();
			String sql = "SELECT * FROM plato WHERE restid = ?;";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, restId);
			
			ResultSet rs = pstmt.executeQuery();
			while ( rs.next() ) {
			    section = null;
			    dish = new Dish(rs.getInt("id"), rs.getString("nombre"), rs.getFloat("precio"), rs.getString("descripcion"));
			    seccion = rs.getString("seccion");
			    for (Section sect : sections) {
					if(sect.getName().equals(seccion)){
						section = sect;
					}
				}
			    if(section == null){
			    	section = new Section(seccion, new LinkedList<Dish>());
			    	sections.add(section);
			    }
			    section.addDish(dish);
			 }
			try {
				menu = new Menu(sections);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return menu;
	}
	
	public Restaurant getRestaurant(String name, String street, int number, String neighborhood, String city, String province, int floor, String apartment) {
		//int addressId = getAddressId(street, number, neighborhood, city, province, floor, apartment);
		Address address = new Address(street, number, floor, apartment, neighborhood, city, province);
		int addressId = AddressDAO.getInstance().getAddressId(address);
				if(addressId == -1){
			//app error!
			return null;
		}
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
				rest = new Restaurant(restId, rs.getString("nombre"), rs.getDouble("montomin"), rs.getFloat("desde"), rs.getFloat("hasta"), address, getTypesOfFoodByRestId(restId), getMenuByRestId(restId));
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
