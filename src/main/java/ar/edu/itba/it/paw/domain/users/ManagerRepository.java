package ar.edu.itba.it.paw.domain.users;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.domain.address.Neighborhood;
import ar.edu.itba.it.paw.domain.common.AbstractHibernateRepository;
import ar.edu.itba.it.paw.domain.report.AdminCard;
import ar.edu.itba.it.paw.domain.report.Card;
import ar.edu.itba.it.paw.domain.report.CardReport;
import ar.edu.itba.it.paw.domain.restaurant.Dish;
import ar.edu.itba.it.paw.domain.restaurant.Order;
import ar.edu.itba.it.paw.domain.restaurant.Restaurant;
import ar.edu.itba.it.paw.exceptions.DuplicateDishException;
import ar.edu.itba.it.paw.exceptions.InvalidPriceException;
import ar.edu.itba.it.paw.exceptions.InvalidSectionName;
import ar.edu.itba.it.paw.exceptions.NoManagersAvailableException;
import ar.edu.itba.it.paw.exceptions.NoRestaurantException;
import ar.edu.itba.it.paw.web.managers.DetailCard;

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
	public LinkedList<CardReport> getReport(User manager, Date from, Date to) {
		LinkedList<CardReport> reportPreview = new LinkedList<CardReport>();
		List<Restaurant> rests = find("from Restaurant r where ? in elements(r.managers) and r.id in (select o.rest.id from Order o where o.status = 0 group by o.rest.id order by o.rest.id)", manager);
		
		int cant;
		LinkedList<Card> cards = new LinkedList<Card>();
		    
	    for (Restaurant rest : rests) {
	    	List<Neighborhood> neighs = find("from Neighborhood where id in (select o.user.address.neighborhood.id from Order o where o.status = 0 and o.rest.id = ? and o.time >= ? and o.time <= ? group by o.user.address.neighborhood)", rest.getId(), from, to);
	    	for (Neighborhood neighborhood : neighs) {
				cant = 0;
				List<Integer> amounts = find("select count(*) from Order o where o.status = 0 and o.rest.id = ? and o.time >= ? and o.time <= ? and o.user.address.neighborhood.id = ? ", rest.getId(), from, to, neighborhood.getId());
				if (amounts.size() > 0) {
					cant = new Integer(String.valueOf(amounts.get(0)));
				}
				if(cant > 0){
					cards.addLast(new Card(neighborhood, cant));
				}
	    	}
	    	reportPreview.addLast(new CardReport(rest, cards, from, to));
	    }
	    return reportPreview;
	}
	
	@Override
	public List<DetailCard> getReportDetail(User manager, int restId, int neighId, Date from, Date to) {
		List<DetailCard> details = new LinkedList<DetailCard>();
		Session session=null;
	    try 
	    {
		    Session sessionSQL = super.getSession();
		    SQLQuery query = null;
	    	query = (SQLQuery) sessionSQL.createSQLQuery("select to_char(horario, 'dd-mm-yyyy'), extract(hour from horario), COUNT(*) from pedido where restid = ? and horario >= ? and horario <= ? and userid in (select u.userid from usuario u where u.dirid in (select a.id from direccion a where a.barrioid = ?)) group by horario, extract(hour from horario)");
	    	query.setParameter(0, restId);
	    	query.setParameter(1, from);
	    	query.setParameter(2, to);
	    	query.setParameter(3, neighId);
	    	
		    List<Object[]> rows = query.list();
		    for (Object[] row: rows) {
		    	System.out.println();
		    	
		    	details.add(new DetailCard(String.valueOf(row[0]), String.valueOf(row[1]), Integer.valueOf(String.valueOf(row[2]))));
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
	    return details;
	}
	
	@Override
	public List<AdminCard> getAdminReport(Date from, Date to) {
		List<AdminCard> report = new LinkedList<AdminCard>();
		List<Restaurant> rests = find("from Restaurant");
		List<Order> orders = new LinkedList<Order>();
		for (Restaurant r : rests) {
			orders = find("from Order o where o.time >= ? and o.time <= ? and o.rest = ?", from, to, r);
			if(orders.size() > 0){
				report.add(new AdminCard(r.getName(), orders.size()));
			}
		}
	    return report;
	}

	
}
