package ar.edu.itba.it.paw.domain.users;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.domain.common.AbstractHibernateRepository;
import ar.edu.itba.it.paw.domain.restaurant.Dish;
import ar.edu.itba.it.paw.domain.restaurant.Restaurant;
import ar.edu.itba.it.paw.domain.restaurant.RestaurantRepository;
import ar.edu.itba.it.paw.exceptions.NoManagersAvailableException;
import ar.edu.itba.it.paw.exceptions.NoRestaurantException;

@Repository
public class ManagerRepository extends AbstractHibernateRepository{

	private RestaurantRepository restaurantRepository;
	private CredentialRepository credentialRepository;
	
	@Autowired
	public ManagerRepository(SessionFactory sessionFactory, RestaurantRepository restaurantRepo, CredentialRepository credentialRepo) {
		super(sessionFactory);
		this.restaurantRepository = restaurantRepo;	
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

	
	@SuppressWarnings("unchecked")
	public Restaurant getRestaurant(User manager) throws NoRestaurantException {
		Restaurant rest = null;
		
		Session session=null;
	    try 
	    {
		    Session sessionSQL = super.getSession();
		    SQLQuery query = (SQLQuery) sessionSQL.createSQLQuery("select restid from gerente where gerente.userid = ?").setParameter(0, manager.getId()); 
		    ArrayList<Object> rows = (ArrayList<Object>) query.list();

		    for (int i = 0; i < rows.size(); i++) {
		    	rest = this.restaurantRepository.getById((Integer) rows.get(i));
			}
	    }
	    catch(Exception e)
	    {
	    	throw new NoRestaurantException(manager);
	    }
	    finally
	    {
	        if(session !=null && session.isOpen())
	        {
	          session.close();
	          session=null;
	        }
	    }
	    return rest;
	}

	public boolean existsDish(String dish) {
		return !find("from Dish where nombre like ?", dish).isEmpty();
	}
	
	public void setManager(int userId, int restId) {
		Session session=null;
	    try 
	    {
		    Session sessionSQL = super.getSession();
		    SQLQuery queryInsert = (SQLQuery) sessionSQL.createSQLQuery("INSERT INTO gerente (userid, restid) VALUES (?, ?)"); 
		    queryInsert.setParameter(0, userId);
		    queryInsert.setParameter(1, restId);
		    queryInsert.executeUpdate();
		    this.credentialRepository.setRol("manager", userId);
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
	
	public boolean validateUser(int userid) throws Exception {
		try {
			Credential cred = credentialRepository.get(userid);
			if (cred == null || !(cred.getRol().equals("usuario"))){
				return false;	
			}
		} catch (Exception e) {
			throw new Exception("bad parameter");
		}
		return true;
	}

	public List<Credential> getManagersAvailables() throws NoManagersAvailableException {
		return credentialRepository.getManagersAvailables();
	}
	
	public boolean addManager(int userid, int restid) {
		try {
			if (!validateUser(userid) || !restaurantRepository.validateId(restid))
				return false;
			setManager(userid, restid);
			return true;
		} catch (Exception e) {
			return false;
		}	
	}
}
