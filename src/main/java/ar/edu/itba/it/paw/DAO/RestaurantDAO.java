package ar.edu.itba.it.paw.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ar.edu.itba.it.paw.models.Address;
import ar.edu.itba.it.paw.models.Dish;
import ar.edu.itba.it.paw.models.Menu;
import ar.edu.itba.it.paw.models.Restaurant;
import ar.edu.itba.it.paw.models.Section;

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
			Restaurant res = new Restaurant(id, sr.getString("nombre"), sr.getFloat("montomin"), sr.getFloat("desde"), sr.getFloat("hasta"), address, tipos, menu, sr.getFloat("costoenvio"));		
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
				rests.add(new Restaurant(restId, sr.getString("nombre"), sr.getFloat("montomin"), sr.getFloat("desde"), sr.getFloat("hasta"), address, tipos, menu, sr.getFloat("costoenvio")));
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
				rest.add(new Restaurant(restId, sr.getString("nombre"), sr.getFloat("montomin"), sr.getFloat("desde"), sr.getFloat("hasta"), address, tipos, menu, sr.getFloat("costoenvio")));
			}
			sr.close();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rest;
		
	}
	
	protected List<String> getTypesOfFoodByRestId(int restId){
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
	
	protected Menu getMenuByRestId(int restId){
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
		List<Integer> addressIds = AddressDAO.getInstance().getIds(address);
				if(addressIds.isEmpty()){
			//app error!
			return null;
		}
		Restaurant rest = null;

		boolean found = false;
		for (int i=0; i<addressIds.size() && !found; i++) {
			rest = matchRestAddress(name, addressIds.get(i));
			if(rest != null){
				found = true;
				rest.setAddress(address);
			}
		}
		return rest;
	}
	
	protected Restaurant matchRestAddress(String name, int addressId){
		int restId = -1;
		Restaurant rest = null;
		try{
			Connection conn = DBManager.getInstance().getConnection();
			String sql = "SELECT * FROM restaurante WHERE nombre like ? and dirid = ?;";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setInt(2, addressId);
			ResultSet rs = pstmt.executeQuery();	 
			while ( rs.next() ) {
				restId = rs.getInt("id");
				rest = new Restaurant(restId, rs.getString("nombre"), rs.getDouble("montomin"), rs.getFloat("desde"), rs.getFloat("hasta"), null, getTypesOfFoodByRestId(restId), getMenuByRestId(restId), rs.getFloat("costoenvio"));
			 }
	         rs.close();
	         pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rest;
	}
	
	public void setRestaurant(Restaurant rest) throws Exception {
		String sql = "INSERT INTO restaurante (dirid, nombre, descripcion, desde, hasta, montomin, costoenvio) VALUES (?, ?, ?, ?, ?, ?, ?);";
		validateAddress(rest.getAddress(), rest.getName());
		int addressId = AddressDAO.getInstance().setAddress(rest.getAddress());
		if(addressId == -1){
			System.out.println("direccion -1");
			return; //TODO: throw exception ??
		}
		
		Connection dbConnection;
		DBManager db = DBManager.getInstance();
		try {
			dbConnection = db.getConnection();
			PreparedStatement pstmt = dbConnection.prepareStatement(sql);
			pstmt.setInt(1, addressId);
			pstmt.setString(2, rest.getName());
			pstmt.setString(3, rest.getDescription());
			pstmt.setFloat(4, rest.getStartService());
			pstmt.setFloat(5, rest.getEndService());
			pstmt.setDouble(6, rest.getMinimumPurchase());
			pstmt.setFloat(7, rest.getCost());
			pstmt.execute();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int id = getRestaurantId(addressId);
		if (id == -1) {
			System.out.println("no restaurant id");
			return;
		}
		setTypes(rest.getTypesOfFood(), id);
	}
	
	private void validateAddress(Address address, String name) throws Exception {
		String sql = "SELECT * FROM restaurante WHERE dirid = ? AND nombre = ?";
		ArrayList<Integer> addressIds = AddressDAO.getInstance().getAddressesIds(address);
		Connection dbConnection;
		DBManager db = DBManager.getInstance();
		dbConnection = db.getConnection();
		try {
			for (Integer id : addressIds) {
				PreparedStatement pstmt = dbConnection.prepareStatement(sql);
				pstmt.setInt(1, id);
				pstmt.setString(2, name);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					throw new Exception("Address already in use");
				}
				rs.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void setTypes(List<String> types, int id) {
		String sql = "INSERT INTO tipos (restid, tipo) VALUES (?, ?);";
		
		Connection dbConnection;
		DBManager db = DBManager.getInstance();
		try {
			dbConnection = db.getConnection();
			for(String type : types) {
				PreparedStatement pstmt = dbConnection.prepareStatement(sql);
				pstmt.setInt(1, id);
				pstmt.setString(2, type);
				pstmt.execute();
				pstmt.close();
			}				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private int getRestaurantId(int dirId) {
		int id = -1;
		String sql = "SELECT id FROM restaurante WHERE dirid = ?";
		Connection dbConnection;
		DBManager db = DBManager.getInstance();
		dbConnection = db.getConnection();
		try {
			PreparedStatement pstmt = dbConnection.prepareStatement(sql);
			pstmt.setInt(1, dirId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				id = rs.getInt("id");
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}

	public boolean validateId(int id) {
		String sql = "SELECT id FROM restaurante WHERE id = ?";
		Connection dbConnection;
		DBManager db = DBManager.getInstance();
		dbConnection = db.getConnection();
		try {
			PreparedStatement pstmt = dbConnection.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			boolean aux = rs.next();
			rs.close();
			return aux;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
