package ar.edu.itba.it.paw.Repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.DAO.DBManager;
import ar.edu.itba.it.paw.models.Address;
import ar.edu.itba.it.paw.models.Dish;
import ar.edu.itba.it.paw.models.Menu;
import ar.edu.itba.it.paw.models.Order;
import ar.edu.itba.it.paw.models.Restaurant;
import ar.edu.itba.it.paw.models.Section;

@Repository
public class RestaurantRepository extends AbstractHibernateRepository{

	private AddressRepository addressRepository;
	
	@Autowired
	public RestaurantRepository(SessionFactory sessionFactory, AddressRepository addressRepository) {
		super(sessionFactory);
		this.addressRepository = addressRepository;
	}
	
	public List<Restaurant> getMostPopular(){
		List<Order> orders = find("from Order");
		//List<Restaurant> results = find("from Restaurant where id in (select distinct restid from Order where max(count(*)) = (select count(restid) from Order group by restid) group by restid)");
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Restaurant> filterBy(String typeOfFood ) {
		//List<Restaurant> rests = super.getSession().createSQLQuery("Select * FROM Restaurant WHERE id in (select distinct restid from tipos where tipo = ?)").setString(1, typeOfFood).setResultTransformer(Transformers.aliasToBean(Restaurant.class)).list();
		List<Restaurant> rests = null;
		Session session=null;
	    try 
	    {
		    Session sessionSQL = super.getSession();
		    Transaction tx = sessionSQL.beginTransaction();
		    SQLQuery query = (SQLQuery) sessionSQL.createSQLQuery("Select * FROM restaurante WHERE id in (select distinct restid from tipos where tipo like ?)").setParameter(0, typeOfFood); 
		    query.addScalar("nombre", Hibernate.STRING);
		    rests = query.setResultTransformer(Transformers.aliasToBean(Restaurant.class)).list();
		    tx.commit();
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	    finally
	    {
	        if(session !=null && session.isOpen())
	        {
	          session.close();
	          session=null;
	        }
	    }
		//return find("FROM Restaurant WHERE id in (select distinct restid from tipos where tipo = ?)", typeOfFood);
		return rests;
	}
	
	public Restaurant getById(int id) {
		List<Restaurant> rests = find("FROM Restaurant WHERE id = ?", id);
		if(rests.isEmpty()){
			return null;
		}
		Restaurant r = rests.get(0);
		Menu menu = getMenuByRestId(id);
		//Address address = addressDao.getAddressById(getAddressId(id));
		//Hibernate thing:
		Address address = getAddressByRestId(id);
		List<String> tipos = getTypesOfFoodByRestId(id);
		Restaurant rest = new Restaurant(id, r.getNombre(), r.getMinimumPurchase(), r.getStartService(), r.getEndService(), address, tipos, menu, r.getCost());		
		return rest;
	}
	
	private Address getAddressByRestId(int restId) {
		return addressRepository.getByRestId(restId);
	}

	public List<Restaurant> getLastWeekAdded() {
		List<Restaurant> rests = find("FROM Restaurant WHERE CURRENT_DATE - DATE(regis) <= 7");

		Menu menu;
		List<String> tipos;
		Address address;
		for (Restaurant r : rests) {
			menu = getMenuByRestId(r.getId());
			r.setMenu(menu);
			tipos = getTypesOfFoodByRestId(r.getId());
			r.setTypesOfFood(tipos);
			address = getAddressByRestId(r.getId());
			r.setAddress(address);
		}
		return rests;	
	}

	public List<Restaurant> getAll() {
		List<Restaurant> results = find("FROM Restaurant");
		Menu menu;
		List<String> tipos;
		Address address;
		for (Restaurant r : results) {
			menu = getMenuByRestId(r.getId());
			r.setMenu(menu);
			tipos = getTypesOfFoodByRestId(r.getId());
			r.setTypesOfFood(tipos);
			address = getAddressByRestId(r.getId());
			r.setAddress(address);
		}
		return results;
	}
	
	public List<String> getTypesOfFoodByRestId(int restId){
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
	
	public Menu getMenuByRestId(int restId){
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
	
	/*public Restaurant getRestaurant(String name, Address address){//String street, int number, String neighborhood, String city, String province, int floor, String apartment) {
		//int addressId = getAddressId(street, number, neighborhood, city, province, floor, apartment);
		//Address address = new Address(street, number, floor, apartment, neighborhood, city, province);
		List<Integer> addressIds = addressDao.getIds(address);
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
				rest.setCalifications(calificationDAO.getCalifications(rest));
			}
		}
		return rest;
	}
	
	public Restaurant matchRestAddress(String name, int addressId){
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
		int addressId = addressDao.setAddress(rest.getAddress());
		if(addressId == -1){
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
			throw new Exception("No tuvo dirección");
		}
		setTypes(rest.getTypesOfFood(), id);
	}
	
	private void validateAddress(Address address, String name) throws Exception {
		String sql = "SELECT * FROM restaurante WHERE dirid = ? AND nombre = ?";
		List<Integer> addressIds = addressDao.getAddressesIds(address);
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
	*/
}
