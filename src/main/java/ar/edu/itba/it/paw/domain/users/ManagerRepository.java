package ar.edu.itba.it.paw.domain.users;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.domain.common.AbstractHibernateRepository;
import ar.edu.itba.it.paw.domain.restaurant.Dish;
import ar.edu.itba.it.paw.domain.restaurant.Restaurant;
import ar.edu.itba.it.paw.exceptions.NoManagersAvailableException;
import ar.edu.itba.it.paw.exceptions.NoRestaurantException;

@Repository
public class ManagerRepository extends AbstractHibernateRepository{

	private CredentialRepository credentialRepository;
	
	@Autowired
	public ManagerRepository(SessionFactory sessionFactory, CredentialRepository credentialRepo) {
		super(sessionFactory);
		this.credentialRepository = credentialRepo;
	}

	public void addDish(Restaurant rest, String section, String dish, int price, String desc) {
		if(Integer.valueOf(price) < 0 || dish == null || desc == null){
			//precio menor a 0 excp
			return;
		}
		if(rest == null){
			//app error
			return;
		}
		if(!existsSection(section)){
			//no existe seccion
			return;
		}
		if(existsDish(dish)){
			//ya existe
			return;
		}
		Dish d = new Dish(rest, dish, price, desc, section);
		int id = (int) save(d);
		d.setId(id);
	}
	
	public boolean existsSection(String section) {
		return section != null && section.length() > 0;
	}

	public Restaurant getRestaurant(User manager) throws NoRestaurantException {
		List<Restaurant> restaurants = find("from Restaurant r where ? in elements(r.managers)", manager); 
		if (!restaurants.isEmpty()) {
			return restaurants.get(0);
		}
		return null;
	}

	public boolean existsDish(String dish) {
		return !find("from Dish where nombre like ?", dish).isEmpty();
	}
	
	public List<Credential> getManagersAvailables() throws NoManagersAvailableException {
		return credentialRepository.getManagersAvailables();
	}
		
	public void setManager(User manager) {
		this.credentialRepository.setRol("manager", manager);
	}
}
