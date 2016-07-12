package ar.edu.itba.it.paw.domain.restaurant;

import java.util.Comparator;
import java.util.Iterator;
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

		Iterator<ClosingPeriod> it = cplist.iterator();
		while (it.hasNext()) {
			if (it.next().getRestaurant().getId() != restaurant.getId()) {
				it.remove();
			}
		}

		cplist.sort(new Comparator<ClosingPeriod>() {

			@Override
			public int compare(ClosingPeriod o1, ClosingPeriod o2) {
				if (o1.getFrom().compareTo(o2.getFrom()) == 0) {
					return o2.getTo().compareTo(o1.getTo());
				}
				return o2.getFrom().compareTo(o1.getFrom());
			}

		});
		
		return cplist;
	}

	public ClosingPeriod getLastClosingPeriod(Restaurant restaurant) {
		List<ClosingPeriod> cplist = getClosingHistorial(restaurant);
		if (cplist.isEmpty())
			return null;
		return cplist.get(0);
	}

	public void add(ClosingPeriod closingPeriod) {
		save(closingPeriod);
	}

}
