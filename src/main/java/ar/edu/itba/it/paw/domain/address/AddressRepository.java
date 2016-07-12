package ar.edu.itba.it.paw.domain.address;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.domain.common.AbstractHibernateRepository;

@Component
public class AddressRepository extends AbstractHibernateRepository implements AddressRepositoryType {

	@Autowired
	public AddressRepository(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	public Neighborhood	getneighById(int id) {
		List<Neighborhood> l = find("FROM Neighborhood WHERE id=?",id);
		return l.get(0);
	}
	
	public List<Neighborhood> getNeigh() {

		List<Neighborhood> l = find("FROM Neighborhood");
		return l;
	}

	public Serializable saveAddress(Address address) {
		return save(address);
	}

}
