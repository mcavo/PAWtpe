package ar.edu.itba.it.paw.domain.restaurant;

import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.domain.address.AddressRepository;
import ar.edu.itba.it.paw.domain.common.AbstractHibernateRepository;
import ar.edu.itba.it.paw.domain.users.User;

@Repository
public class RestaurantRepository extends AbstractHibernateRepository{

	private AddressRepository addressRepository;
	
	@Autowired
	public RestaurantRepository(SessionFactory sessionFactory, AddressRepository addressRepository) {
		super(sessionFactory);
		this.addressRepository = addressRepository;
	}
	
	public List<Restaurant> getMostPopular(){
		List<Restaurant> rests = null;
		rests = find("select r from Restaurant r WHERE r in "
				+ "(select o1.rest from Order o1 group by o1.rest.id having count(o1) >= "
				+ "(select count(o2) from Order o2 where o2.rest.id <> o1.rest.id)) order by name asc");
	    Menu menu;
		for (Restaurant r : rests) {
			menu = getMenuByRestaurant(r);
			r.setMenu(menu);
		}
		return rests;
	}
	
	public List<Restaurant> filterBy(String typeOfFood ) {
		List<Restaurant> rests = null;
		rests = find("from Restaurant r where ? in elements(r.typesOfFood) order by name asc", typeOfFood); 
	    Menu menu;
		for (Restaurant r : rests) {
			menu = getMenuByRestaurant(r);
			r.setMenu(menu);
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
		r.setMenu(menu);		
		return r;
	}
	
	public List<Restaurant> getLastWeekAdded() {
		List<Restaurant> rests = find("FROM Restaurant WHERE CURRENT_DATE - DATE(regis) <= 7 ORDER BY name ASC");

		Menu menu;
		for (Restaurant r : rests) {
			menu = getMenuByRestaurant(r);
			r.setMenu(menu);
		}
		return rests;	
	}

	public List<Restaurant> getAll() {
		List<Restaurant> results = find("FROM Restaurant order by name asc");
		Menu menu;
		for (Restaurant r : results) {
			menu = getMenuByRestaurant(r);
			r.setMenu(menu);
		}
		return results;
	}
		
	public Menu getMenuByRestaurant(Restaurant r){
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
		
		return new Menu(sections);
	}
	
	public boolean setRestaurant( Restaurant rest) {
		Timestamp now = new Timestamp((new Date()).getTime());
		rest.setRegis(now);
		return saveRestaurant(rest) != 0; //TODO: revisar si esto funciona 
	}
	
	public int saveRestaurant(Restaurant rest) {
		addressRepository.saveAddress(rest.getAddress());
		return (int) save(rest);
	}
	
	public boolean validateId(int id) {
		return get(Restaurant.class, id) != null;
	}

	public boolean userCanOrder(User user, Restaurant rest) {
		return !find("from Restaurant r where r.id = ? and  (Select u.address.neighborhood from User u where u.id = ?) in elements(r.deliveryneigh)", rest.getId(), user.getId()).isEmpty();
	}
	
}
