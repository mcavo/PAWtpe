package ar.edu.itba.it.paw.domain.restaurant;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.domain.address.Address;
import ar.edu.itba.it.paw.domain.address.AddressRepository;
import ar.edu.itba.it.paw.domain.common.AbstractHibernateRepository;
import ar.edu.itba.it.paw.domain.users.User;

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
	
	public List<Restaurant> getMostPopular(){
		List<Restaurant> rests = null;
		rests = find("select r from Restaurant r WHERE r in "
				+ "(select o1.rest from Order o1 group by o1.rest.id having count(o1) >= "
				+ "(select count(o2) from Order o2 where o2.rest.id <> o1.rest.id))");
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
		if(rests!=null)
			rests.sort(new Comparator<Restaurant> () {

				public int compare(Restaurant o1, Restaurant o2) {
					return o1.getName().compareTo(o2.getName());
				}
			
			});
		return rests;
	}
	
	@SuppressWarnings("unchecked")
	public List<Restaurant> filterBy(String typeOfFood ) {
		List<Restaurant> rests = null;
		Session session=null;
	    try 
	    {
		    Session sessionSQL = super.getSession();
		    //Transaction tx = sessionSQL.beginTransaction();
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
		    //tx.commit();
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
		if(rests!=null)
			rests.sort(new Comparator<Restaurant> () {

				public int compare(Restaurant o1, Restaurant o2) {
					return o1.getName().compareTo(o2.getName());
				}
			
			});
		return rests;
	}
	
	public Restaurant getById(int id) {
		List<Restaurant> rests = find("FROM Restaurant WHERE id = ?", id);
		if(rests.isEmpty()){
			return null;
		}
		Restaurant r = rests.get(0);
		Menu menu = getMenuByRestaurant(r);
		Address address = getAddressByRestaurant(r);
		List<String> tipos = getTypesOfFoodByRestaurant(r);
		Restaurant rest = new Restaurant(id, r.getName(), r.getMinamount(), r.getFrom(), r.getTo(), address, tipos, menu, r.getDelamount(),r.getDeliveryfrom(),r.getDeliveryto(),r.getDeliveryneigh());		
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
		if(rests!=null)
			rests.sort(new Comparator<Restaurant> () {
				public int compare(Restaurant o1, Restaurant o2) {
					return o1.getName().compareTo(o2.getName());
				}
			
			});
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
		if(results!=null)
			results.sort(new Comparator<Restaurant> () {
				public int compare(Restaurant o1, Restaurant o2) {
					return o1.getName().compareTo(o2.getName());
				}
			
			});
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getTypesOfFoodByRestaurant(Restaurant r){
		List<String> tof = new LinkedList<String>();
		Session session=null;
	    try 
	    {
		    Session sessionSQL = super.getSession();
		    //Transaction tx = sessionSQL.beginTransaction();
		    SQLQuery query = (SQLQuery) sessionSQL.createSQLQuery("SELECT tipo FROM tipos WHERE restid = ?").setParameter(0, r.getId()); 
		    tof = query.list();
		    //tx.commit();
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
			e.printStackTrace();
		}

		return menu;
	}
	
	//este metodo deberia volar
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
	}
	
	public boolean setRestaurant( Restaurant rest) {
		int addressId = this.addressRepository.setAddress(rest.getAddress());
		if(addressId == -1){
			return false;
		}
		rest.setDirid(addressId);
		Timestamp now = new Timestamp((new Date()).getTime());
		rest.setRegis(now);
		int id = saveRestaurant(rest);
		rest.setId(id);
		int idBydir = getRestaurantId(addressId);
		if (idBydir == -1) {
			return false;
		}
		try {
			setTypes(rest.getTypesOfFood(), id);
		} catch(Exception f) {
			return false;
		}
		return true;
	}
	
	private void setTypes(List<String> types, int id) {
		for(String type : types) {
			setByOne(type, id);
		}
	}
	
	private void setByOne(String type, int id){
		Session session=null;
	    try 
	    {
		    Session sessionSQL = super.getSession();
		    //Transaction tx = sessionSQL.beginTransaction();
		    SQLQuery query = (SQLQuery) sessionSQL.createSQLQuery("INSERT INTO tipos (restid, tipo) VALUES (?, ?);");
		    query.setParameter(0, id); 
		    query.setParameter(1, type);
		    query.executeUpdate();
		    //tx.commit();
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
	
	private int getRestaurantId(int dirId) {
		List<Restaurant> rests = find("from Restaurant where dirid = ?", dirId);
		if(!rests.isEmpty()){
			return rests.get(0).getId();
		}
		return -1;
	}
	
	public int saveRestaurant(Restaurant rest) {
//		save(rest);
		return (int) save(rest);
	}
	
	public boolean validateId(int id) {
		return get(Restaurant.class, id) != null;
	}

	@SuppressWarnings("unchecked")
	public boolean userCanOrder(User user, Restaurant rest) {
		int neighId = user.getAddress().getNeighborhood().getId();
		boolean out = false;
		
		Session session=null;
	    try 
	    {
		    Session sessionSQL = super.getSession();
		    //Transaction tx = sessionSQL.beginTransaction();
		    SQLQuery query = (SQLQuery) sessionSQL.createSQLQuery("SELECT * FROM delivery WHERE restid = ? and barrioid = ?");
		    query.setParameter(0, rest.getId()); 
		    query.setParameter(1, neighId); 
		    List<Object[]> rows = query.list();
		    //tx.commit();
		    
		    for (Object[] row: rows) {
		    	out = true;
		    	double cost = (double) row[2];
		    	rest.setDelamount(cost);
		    }
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
	    return out;
	}
}
