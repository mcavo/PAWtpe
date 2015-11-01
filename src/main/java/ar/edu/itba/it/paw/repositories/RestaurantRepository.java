package ar.edu.itba.it.paw.repositories;

import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.models.Address;
import ar.edu.itba.it.paw.models.Dish;
import ar.edu.itba.it.paw.models.Menu;
import ar.edu.itba.it.paw.models.Restaurant;
import ar.edu.itba.it.paw.models.Section;

@Repository
public class RestaurantRepository extends AbstractHibernateRepository{

	private AddressRepository addressRepository;
	private CalificationRepository calificationRepository;
	
	@Autowired
	public RestaurantRepository(SessionFactory sessionFactory, AddressRepository addressRepository, CalificationRepository calificationRepository) {
		super(sessionFactory);
		this.addressRepository = addressRepository;
		this.calificationRepository = calificationRepository;
	}
	
	@SuppressWarnings("unchecked")
	public List<Restaurant> getMostPopular(){
		//List<Restaurant> results = find("from Restaurant where id in (select distinct restid from Order where max(count(*)) = (select count(restid) from Order group by restid) group by restid)");
		List<Restaurant> rests = null;
		Session session=null;
	    try 
	    {
		    Session sessionSQL = super.getSession();
		    Transaction tx = sessionSQL.beginTransaction();
		    //ARREGLAR LA QUERY!
		    SQLQuery query = (SQLQuery) sessionSQL.createSQLQuery("Select * FROM restaurante WHERE id in "
		    											+ "(select p1.restid from pedido p1 group by p1.restid having count(*) >= "
		    												+ "(select count(*) from pedido p2 where p2.restid <> p1.restid))"); 
		    query.addScalar("nombre", Hibernate.STRING);
		    query.addScalar("montomin", Hibernate.DOUBLE);
		    query.addScalar("desde", Hibernate.FLOAT);
		    query.addScalar("hasta", Hibernate.FLOAT);
		    query.addScalar("id", Hibernate.INTEGER);
		    query.addScalar("descripcion", Hibernate.STRING);
		    query.addScalar("regis", Hibernate.TIMESTAMP);
		    query.addScalar("costoenvio", Hibernate.FLOAT);
		    query.addScalar("dirid", Hibernate.INTEGER);
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
	    Menu menu;
		List<String> tipos;
		Address address;
		for (Restaurant r : rests) {
			menu = getMenuByRestaurant(r);
			r.setMenu(menu);
			tipos = getTypesOfFoodByRestaurant(r);
			r.setTypesOfFood(tipos);
			address = getAddressByRestaurant(r);
			r.setAddress(address);
			r.setCalifications(calificationRepository.getCalifications(r));
		}
		return rests;
	}
	
	@SuppressWarnings("unchecked")
	public List<Restaurant> filterBy(String typeOfFood ) {
		List<Restaurant> rests = null;
		Session session=null;
	    try 
	    {
		    Session sessionSQL = super.getSession();
		    Transaction tx = sessionSQL.beginTransaction();
		    SQLQuery query = (SQLQuery) sessionSQL.createSQLQuery("Select * FROM restaurante WHERE id in (select distinct restid from tipos where tipo like ?)").setParameter(0, typeOfFood); 
		    query.addScalar("nombre", Hibernate.STRING);
		    query.addScalar("montomin", Hibernate.DOUBLE);
		    query.addScalar("desde", Hibernate.FLOAT);
		    query.addScalar("hasta", Hibernate.FLOAT);
		    query.addScalar("id", Hibernate.INTEGER);
		    query.addScalar("descripcion", Hibernate.STRING);
		    query.addScalar("regis", Hibernate.TIMESTAMP);
		    query.addScalar("costoenvio", Hibernate.FLOAT);
		    query.addScalar("dirid", Hibernate.INTEGER);
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
	    Menu menu;
		List<String> tipos;
		Address address;
		for (Restaurant r : rests) {
			menu = getMenuByRestaurant(r);
			r.setMenu(menu);
			tipos = getTypesOfFoodByRestaurant(r);
			r.setTypesOfFood(tipos);
			address = getAddressByRestaurant(r);
			r.setAddress(address);
			r.setCalifications(this.calificationRepository.getCalifications(r));
		}
		return rests;
	}
	
	public Restaurant getById(int id) {
		List<Restaurant> rests = find("FROM Restaurant WHERE id = ?", id);
		if(rests.isEmpty()){
			return null;
		}
		Restaurant r = rests.get(0);
		Menu menu = getMenuByRestaurant(r);
		//Address address = addressDao.getAddressById(getAddressId(id));
		//Hibernate thing:
		Address address = getAddressByRestaurant(r);
		List<String> tipos = getTypesOfFoodByRestaurant(r);
		Restaurant rest = new Restaurant(id, r.getNombre(), r.getMontomin(), r.getDesde(), r.getHasta(), address, tipos, menu, r.getCostoenvio());		
		rest.setCalifications(this.calificationRepository.getCalifications(r));
		return rest;
	}
	
	private Address getAddressByRestaurant(Restaurant r) {
		return addressRepository.getByRestaurant(r);
	}

	public List<Restaurant> getLastWeekAdded() {
		List<Restaurant> rests = find("FROM Restaurant WHERE CURRENT_DATE - DATE(regis) <= 7");

		Menu menu;
		List<String> tipos;
		Address address;
		for (Restaurant r : rests) {
			menu = getMenuByRestaurant(r);
			r.setMenu(menu);
			tipos = getTypesOfFoodByRestaurant(r);
			r.setTypesOfFood(tipos);
			address = getAddressByRestaurant(r);
			r.setAddress(address);
			r.setCalifications(this.calificationRepository.getCalifications(r));
		}
		return rests;	
	}

	public List<Restaurant> getAll() {
		List<Restaurant> results = find("FROM Restaurant");
		Menu menu;
		List<String> tipos;
		Address address;
		for (Restaurant r : results) {
			menu = getMenuByRestaurant(r);
			r.setMenu(menu);
			tipos = getTypesOfFoodByRestaurant(r);
			r.setTypesOfFood(tipos);
			address = getAddressByRestaurant(r);
			r.setAddress(address);
			r.setCalifications(calificationRepository.getCalifications(r));
		}
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getTypesOfFoodByRestaurant(Restaurant r){
		List<String> tof = new LinkedList<String>();
		Session session=null;
	    try 
	    {
		    Session sessionSQL = super.getSession();
		    Transaction tx = sessionSQL.beginTransaction();
		    SQLQuery query = (SQLQuery) sessionSQL.createSQLQuery("SELECT tipo FROM tipos WHERE restid = ?").setParameter(0, r.getId()); 
		    tof = query.list();
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

		return tof;
	}
	
	public Menu getMenuByRestaurant(Restaurant r){
		Menu menu = null;
		LinkedList<Section> sections = new LinkedList<Section>();
		Section section = null;
		String seccion = null;
		
		List<Dish> dishes = find("from Dish where restid = ?", r.getId());
		for (Dish d : dishes) {
			section = null;
			seccion = d.getSection();
			for (Section sect : sections) {
				if(sect.getName().equals(seccion)){
					section = sect;
				}
			}
			if(section == null){
			    section = new Section(seccion, new LinkedList<Dish>());
			    sections.add(section);
			}
			section.addDish(d);
		}
		
		try {
			menu = new Menu(sections);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return menu;
	}
	
	public Restaurant getRestaurant(String name, Address address){
		List<Integer> addressIds = this.addressRepository.getIds(address);
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
	
	public Restaurant matchRestAddress(String name, int addressId){
		List<Restaurant> rests = find("from Restaurant where nombre like ? and dirid = ?", name, addressId);
		if(rests.isEmpty()){
			return null;
		}
		Restaurant r = rests.get(0);
		r.setTypesOfFood(getTypesOfFoodByRestaurant(r));
		r.setMenu(getMenuByRestaurant(r));
		r.setCalifications(calificationRepository.getCalifications(r));
		return r;
		
		/*int restId = -1;
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
		*/
	}
	
	public void setRestaurant(Restaurant rest) throws Exception {
		validateAddress(rest.getAddress(), rest.getNombre());
		int addressId = this.addressRepository.setAddress(rest.getAddress());
		if(addressId == -1){
			return; //TODO: throw exception ??
		}
		rest.setDirid(addressId);
		Timestamp now = new Timestamp((new Date()).getTime());
		rest.setRegis(now);
		int id = (Integer) save(rest);
		rest.setId(id);
		int idBydir = getRestaurantId(addressId);
		if (idBydir == -1) {
			throw new Exception("No tuvo dirección");
		}
		setTypes(rest.getTypesOfFood(), id);
		
		/*String sql = "INSERT INTO restaurante (dirid, nombre, descripcion, desde, hasta, montomin, costoenvio) VALUES (?, ?, ?, ?, ?, ?, ?);";
		validateAddress(rest.getAddress(), rest.getNombre());
		int addressId = this.addressRepository.setAddress(rest.getAddress());
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
		*/
	}
	
	
	@SuppressWarnings("unused")
	private void validateAddress(Address address, String name) throws Exception {
		List<Integer> addressIds = this.addressRepository.getAddressesIds(address);
		
		List<Address> results = null;
		for (Integer id : addressIds) {
			results = find("from Restaurant where dirid = ? and nombre = ?", id, name);
			if (!results.isEmpty()) {
				throw new Exception("Address already in use");
			}
		}
		
		/*String sql = "SELECT * FROM restaurante WHERE dirid = ? AND nombre = ?";
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
		}*/
	}
	
	@SuppressWarnings("unused")
	private void setTypes(List<String> types, int id) {
		for(String type : types) {
			setByOne(type, id);
		}
		/*
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
		*/
	}
	
	private void setByOne(String type, int id){
		Session session=null;
	    try 
	    {
		    Session sessionSQL = super.getSession();
		    Transaction tx = sessionSQL.beginTransaction();
		    SQLQuery query = (SQLQuery) sessionSQL.createSQLQuery("INSERT INTO tipos (restid, tipo) VALUES (?, ?);");
		    query.setParameter(0, id); 
		    query.setParameter(1, type);
		    query.executeUpdate();
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

	}
	
	
	@SuppressWarnings("unused")
	private int getRestaurantId(int dirId) {
		List<Restaurant> rests = find("from Restaurant where dirid = ?", dirId);
		if(!rests.isEmpty()){
			return rests.get(0).getId();
		}
		return -1;
		/*
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
		*/
	}

	
	public boolean validateId(int id) {
		return get(Restaurant.class, id) != null;
	}
}
