package ar.edu.itba.it.paw.repositories;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.models.Dish;
import ar.edu.itba.it.paw.models.Restaurant;

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

	@SuppressWarnings("unchecked")
	public Restaurant getRestByManagerId(int managerId) {
		Restaurant rest = null;
		Session session=null;
	    try 
	    {
		    Session sessionSQL = super.getSession();
		    Transaction tx = sessionSQL.beginTransaction();
		    //ARREGLAR LA QUERY!
		    SQLQuery query = (SQLQuery) sessionSQL.createSQLQuery("SELECT restid FROM gerente WHERE userid = ?").setParameter(0, managerId); 
		    //query.addScalar("userid", Hibernate.INTEGER);
		    //query.addScalar("restid", Hibernate.INTEGER);
		    List<Object[]> rows = query.list();
		    tx.commit();
		    
		    for (Object[] row: rows) {
		    	rest = this.restaurantRepository.getById((int) row[0]);
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
	    return rest;
	}

	public void addDish(Restaurant rest, String section, String dish, int price, String desc) {
		Dish d = new Dish();
		d.setRest(rest);
		d.setDescription(desc);
		d.setPrice(price);
		d.setProduct(dish);
		d.setSection(section);
		int id = (int) save(d);
		d.setId(id);
	}
	
	public boolean existsSection(String section) {
		return !find("from Dish where seccion like ?", section).isEmpty();
	}

	
	@SuppressWarnings("unchecked")
	public Restaurant getRestaurant(int managerId) {
		Restaurant rest = null;
		
		Session session=null;
	    try 
	    {
		    Session sessionSQL = super.getSession();
		    Transaction tx = sessionSQL.beginTransaction();
		    //ARREGLAR LA QUERY!
		    SQLQuery query = (SQLQuery) sessionSQL.createSQLQuery("select restid from gerente where gerente.userid = ?").setParameter(0, managerId); 
		    //query.addScalar("userid", Hibernate.INTEGER);
		    //query.addScalar("restid", Hibernate.INTEGER);
		    ArrayList<Object> rows = (ArrayList<Object>) query.list();
		    tx.commit();

		    for (int i = 0; i < rows.size(); i++) {
		    	rest = this.restaurantRepository.getById((Integer) rows.get(i));
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
		    Transaction tx = sessionSQL.beginTransaction();
		    //ARREGLAR LA QUERY!
		    SQLQuery queryInsert = (SQLQuery) sessionSQL.createSQLQuery("INSERT INTO gerente (userid, restid) VALUES (?, ?)"); 
		    queryInsert.setParameter(0, userId);
		    queryInsert.setParameter(1, restId);
		    queryInsert.executeUpdate();
		    tx.commit();
		    this.credentialRepository.setRol("gerente", userId);
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
}
