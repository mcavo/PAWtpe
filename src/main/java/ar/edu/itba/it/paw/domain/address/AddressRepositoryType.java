package ar.edu.itba.it.paw.domain.address;

import java.io.Serializable;
import java.util.List;

public interface AddressRepositoryType {
	public Neighborhood	getneighById(int id);
	public List<Neighborhood> getNeigh();
	public Serializable saveAddress(Address address);
}
