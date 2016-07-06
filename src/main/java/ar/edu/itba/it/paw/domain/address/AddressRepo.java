package ar.edu.itba.it.paw.domain.address;

import java.io.Serializable;

public interface AddressRepo {

	public Neighborhood	getneighById(int id);
	public Serializable saveAddress(Address address);
}
