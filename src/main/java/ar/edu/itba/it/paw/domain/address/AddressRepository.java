package ar.edu.itba.it.paw.domain.address;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.domain.common.AbstractHibernateRepository;

@Repository
public class AddressRepository extends AbstractHibernateRepository {

	@Autowired
	public AddressRepository(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

//	public Address getAddressById(int addressId) {
//		Address address = null;
//		List<Address> results = find("FROM Address WHERE id = ?", addressId);
//		if (!results.isEmpty()) {
//			address = results.get(0);
//		}
//		return address;
//	}

//	public int setAddress(Address address) {
//		int id = (Integer) save(address);
//		address.setId(id);
//
//		return id;
//
//	}
	
	public Neighborhood	getneighById(int id) {
		List<Neighborhood> l = find("FROM Neighborhood WHERE id=?",id);
		return l.get(0);
	}
	
	public List<Neighborhood> getNeigh() {

		List<Neighborhood> l = find("FROM Neighborhood");
		return l;
	}
	
//	public Address getByRestaurant(Restaurant rest) {
//		List<Address> result = find("FROM Address WHERE id = (select dirid from Restaurant where id = ?)",
//				rest.getId());
//		if (result.isEmpty()) {
//			return null;
//		}
//		return result.get(0);
//	}

	public Serializable saveAddress(Address address) {
		return save(address);
	}

}
