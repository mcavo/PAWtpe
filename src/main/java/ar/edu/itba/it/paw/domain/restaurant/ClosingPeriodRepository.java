package ar.edu.itba.it.paw.domain.restaurant;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.domain.common.AbstractHibernateRepository;

@Component
public class ClosingPeriodRepository extends AbstractHibernateRepository implements ClosingPeriodRepositoryType {
	
	@Autowired
	public ClosingPeriodRepository(SessionFactory sessionFactory, RestaurantRepositoryType restRepository) {
		super(sessionFactory);
	}
	
	public List<ClosingPeriod> getClosingHistorial(Restaurant restaurant) {
		List<ClosingPeriod> cplist = find("FROM ClosingPeriod");
		for(ClosingPeriod cp : cplist) {
			if(cp.getRestaurant().getId() != restaurant.getId()) {
				cplist.remove(cp);
			}
		}
		return cplist;
	}
	
	public void add(ClosingPeriod closingPeriod) {
		save(closingPeriod);
	}

}
