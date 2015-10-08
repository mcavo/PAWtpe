package ar.edu.itba.it.paw.DAO;

import java.util.ArrayList;
import java.util.List;

import ar.edu.itba.it.paw.models.Address;

public interface AddressDAO {

	public Address getAddressById(int addressId);
	
	public int setAddress(Address address);
	
	public int getAddressID(Address address);
	
	public List<Integer> getIds(Address address);
	
	public ArrayList<Integer> getAddressesIds(Address address);
}
