package ar.edu.itba.it.paw.domain.users;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.domain.address.Neighborhood;
import ar.edu.itba.it.paw.domain.common.AbstractHibernateRepository;
import ar.edu.itba.it.paw.domain.report.Card;
import ar.edu.itba.it.paw.domain.report.CardReport;
import ar.edu.itba.it.paw.domain.restaurant.Dish;
import ar.edu.itba.it.paw.domain.restaurant.Restaurant;
import ar.edu.itba.it.paw.exceptions.DuplicateDishException;
import ar.edu.itba.it.paw.exceptions.InvalidPriceException;
import ar.edu.itba.it.paw.exceptions.InvalidSectionName;
import ar.edu.itba.it.paw.exceptions.NoManagersAvailableException;
import ar.edu.itba.it.paw.exceptions.NoRestaurantException;

@Repository
public class ManagerRepository extends AbstractHibernateRepository implements ManagerRepositoryType {


	private CredentialRepository credentialRepository;
	
	@Autowired
	public ManagerRepository(SessionFactory sessionFactory, CredentialRepository credentialRepo) {
		super(sessionFactory);
		this.credentialRepository = credentialRepo;
	}

	public void addDish(Restaurant rest, String section, String dish, int price, String desc) throws NoRestaurantException, InvalidPriceException, DuplicateDishException, InvalidSectionName {
		if(Integer.valueOf(price) < 0 || dish == null || desc == null){
			throw new InvalidPriceException();
		}
		if(rest == null){
			throw new NoRestaurantException();
		}
		if(!validateSection(section)){
			throw new InvalidSectionName();
		}
		if(existsDish(dish)){
			throw new DuplicateDishException();
		}
		Dish d = new Dish(rest, dish, price, desc, section);
		int id = (int) save(d);
		d.setId(id);
	}
	
	private boolean validateSection(String section) {
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

	@Override
	public LinkedList<CardReport> getReport(User manager) {
		LinkedList<CardReport> reportPreview = new LinkedList<CardReport>();
		List<Restaurant> rests = find("from Restaurant r where ? in elements(r.managers) and r.id in (select o.rest.id from Order o where o.status = 0 group by o.rest.id order by o.rest.id)", manager);
		Session session=null;
	    try 
	    {
		    //Session sessionSQL = super.getSession();
		    //SQLQuery query = null;
		    int cant = 0;
		    //int neighbourhoodId;
		    //Card card = null;
		    LinkedList<Card> cards = new LinkedList<Card>();
		    
		    for (Restaurant rest : rests) {
		    	List<Neighborhood> neighs = find("from Neighborhood where id in (select o.user.address.neighborhood.id from Order o where o.status = 0 and o.rest.id = ? group by o.user.address.neighborhood)", rest.getId());
		    	for (Neighborhood neighborhood : neighs) {
					cant = 0;
					List<Integer> amounts = find("select count(*) from Order o where o.status = 0 and o.rest.id = ? and o.user.address.neighborhood.id = ? ", rest.getId(), neighborhood.getId());
					if (amounts.size() > 0) {
						cant = new Integer(String.valueOf(amounts.get(0)));
					}
					if(cant > 0){
						cards.addLast(new Card(neighborhood, cant));
					}
		    	}
		    	
		    	
		    	//query = (SQLQuery) sessionSQL.createSQLQuery("select sum (*), from pedido o where o.estado = 0 and o.restid = ? group by o.user.address.neighborhood;").setParameter(0, rest.getId());
//			    List<Object[]> rows = query.list();
//			    
//			    cant = 0;
//			    neighbourhoodId = -1;
//			    for (Object[] row: rows) {
//			    	cant = (int) row[1];
//			    	neighbourhoodId = (int) row[2];
//			    	cards.addLast(new Card(neighbourhoodId, cant));
//			    }
			    reportPreview.addLast(new CardReport(rest, cards));
			    //rows.clear();
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
	    return reportPreview;
	}
}
