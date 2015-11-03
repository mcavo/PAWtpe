package ar.edu.itba.it.paw.repositories;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.models.Address;
import ar.edu.itba.it.paw.models.Neighborhood;
import ar.edu.itba.it.paw.models.Restaurant;

@Repository
public class AddressRepository extends AbstractHibernateRepository {

	@Autowired
	public AddressRepository(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public Address getAddressById(int addressId) {
		Address address = null;
		List<Address> results = find("FROM Address WHERE id = ?", addressId);
		if (!results.isEmpty()) {
			address = results.get(0);
		}
		return address;
	}

	public int setAddress(Address address) {
		int id = (Integer) save(address);
		address.setId(id);

		return id;

	}

	public int getAddressID(Address address) {
		// TODO: reveer esta query. No sé si es lo mejor. Necesito conseguir el
		// id de la última tupla que agregué.
		int floor = -1;
		String aprt = "";
		if (address.getFloor() != -1) {
			floor = address.getFloor();
		}
		if (address.getApartment() != null && !address.getApartment().isEmpty()) {
			aprt = address.getApartment();
		}
		List<Address> results = find(
				"FROM Address WHERE calle = ? AND localidad = ? AND provincia = ? AND numero = ? AND "
						+ "(piso = ? or piso is null) AND (departamento = ? or departamento is null) AND barrio = ? AND "
						+ "id = (SELECT max(id) FROM Address)",
				address.getStreet(), address.getCity(), address.getProvince(), address.getNumber(), floor, aprt,
				address.getNeighborhood());

		if (results.isEmpty()) {
			return -1;
		}

		return results.get(0).getId();
	}

	public List<Neighborhood> getNeigh() {

		List<Neighborhood> l = find("FROM Neighborhood");
		return l;
	}

	public List<Integer> getIds(Address address) {// String street, int number,
													// String neighborhood,
													// String city, String
													// province, int floor,
													// String apartment) {
		List<Integer> ids = new LinkedList<Integer>();
		List<Address> results = find(
				"FROM Address WHERE calle like ? and numero = ? and barrio like ? and localidad like ? "
						+ "and provincia like ? and (piso = ? or piso is null) and (departamento like ? or "
						+ "departamento is null)",
				address.getStreet(), address.getNumber(), address.getNeighborhood(), address.getCity(),
				address.getProvince(), address.getFloor(), address.getApartment());

		for (Address addr : results) {
			ids.add(addr.getId());
		}
		return ids;
	}

	public List<Integer> getAddressesIds(Address address) {
		return getIds(address);
	}

	protected Address getByRestaurant(Restaurant rest) {
		List<Address> result = find("FROM Address WHERE id = (select dirid from Restaurant where id = ?)",
				rest.getId());
		if (result.isEmpty()) {
			return null;
		}
		return result.get(0);
	}

	public Serializable saveAddress(Address address) {
		return save(address);
	}

}
