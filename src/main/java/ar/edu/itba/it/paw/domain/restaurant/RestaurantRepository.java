package ar.edu.itba.it.paw.domain.restaurant;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.wicket.spring.injection.annot.SpringBean;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.domain.address.AddressRepo;
import ar.edu.itba.it.paw.domain.address.AddressRepository;
import ar.edu.itba.it.paw.domain.common.AbstractHibernateRepository;
import ar.edu.itba.it.paw.domain.users.User;

@Component
public class RestaurantRepository extends AbstractHibernateRepository implements RestaurantRepositoryType{

	@SpringBean
	private AddressRepo addressRepository;
	
	@Autowired
	public RestaurantRepository(SessionFactory sessionFactory/*, AddressRepository addressRepository*/) {
		super(sessionFactory);
		//this.addressRepository = addressRepository;
	}
	
	public List<Restaurant> getMostPopular(){
		List<Restaurant> rests = null;
		rests = find("select r from Restaurant r WHERE r in "
				+ "(select o1.rest from Order o1 group by o1.rest.id having count(o1) >= "
				+ "(select count(o2) from Order o2 where o2.rest.id <> o1.rest.id)) order by name asc");
		return rests;
	}
	
	public List<Restaurant> filterBy(String typeOfFood ) {
		return find("from Restaurant r where ? in elements(r.typesOfFood) order by name asc", typeOfFood); 
	}
	
	public Restaurant getById(int id) {
		List<Restaurant> rests = find("FROM Restaurant WHERE id = ?", id);
		if(rests.isEmpty()){
			return null;
		}
		Restaurant r = rests.get(0);	
		return r;
	}
	
	public List<Restaurant> getLastWeekAdded() {
		return find("FROM Restaurant WHERE CURRENT_DATE - DATE(regis) <= 7 ORDER BY name ASC");
	}

	public List<Restaurant> getAll() {
		return find("FROM Restaurant order by name asc");
	}
	
	public boolean setRestaurant( Restaurant rest) {
		Timestamp now = new Timestamp((new Date()).getTime());
		rest.setRegis(now);
		return saveRestaurant(rest) != 0; 
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
