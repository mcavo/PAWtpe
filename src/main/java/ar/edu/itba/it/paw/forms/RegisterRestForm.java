package ar.edu.itba.it.paw.forms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ar.edu.itba.it.paw.domain.address.Address;
import ar.edu.itba.it.paw.domain.address.AddressRepository;
import ar.edu.itba.it.paw.domain.address.Neighborhood;
import ar.edu.itba.it.paw.domain.restaurant.Restaurant;
public class RegisterRestForm {
	
	private String name;
	private String description;
	private String[] tfood;
	private String street;
	private String number;
	private String floor;
	private String apartment;
	private String neigh;
	private String city;
	private String prov;
	private String from;
	private String to;
	private String minamount;
	private String delamount;
	private String[] delneigh;
	private String deliveryfrom;
	private String deliveryto;
	
	
	public RegisterRestForm() {
	}
	
	public Restaurant build(AddressRepository addressRepo) {
		Address address = getAddress(addressRepo);
		List<String> l = new ArrayList<String>();
		for(String s : tfood) {
			l.add(s);
		}
		Set<Neighborhood> set = new HashSet<Neighborhood>();
		for(String s : delneigh) {
			set.add(new Neighborhood(Integer.parseInt(s)));
		}
		Restaurant r = new Restaurant(-1,name,Float.parseFloat(minamount),from,to,address,l,null,Double.parseDouble(delamount),deliveryfrom,deliveryto, set);
		
		return r;
	}
	
	public Address getAddress(AddressRepository addressRepo) {
		Integer n = null;
		System.out.println("floor: "+floor+";");
		if(number!=null && !number.equals(""))
			n = Integer.parseInt(number);
		Integer f = null;
		if(floor!=null && !floor.equals(""))
			f = Integer.parseInt(floor);
		return new Address(street, n, f, apartment, addressRepo.getneighById(Integer.parseInt(neigh)), city, prov);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String[] getTfood() {
		return tfood;
	}
	public void setTfood(String[] tfood) {
		this.tfood = tfood;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getApartment() {
		return apartment;
	}
	public void setApartment(String apartment) {
		this.apartment = apartment;
	}
	public String getNeigh() {
		return neigh;
	}
	public void setNeigh(String neigh) {
		this.neigh = neigh;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProv() {
		return prov;
	}
	public void setProv(String prov) {
		this.prov = prov;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getMinamount() {
		return minamount;
	}
	public void setMinamount(String minamount) {
		this.minamount = minamount;
	}
	public String getDelamount() {
		return delamount;
	}
	public void setDelamount(String delamount) {
		this.delamount = delamount;
	}
	public String[] getDelneigh() {
		return delneigh;
	}
	public void setDelneigh(String[] delneigh) {
		this.delneigh = delneigh;
	}
	public String getDeliveryfrom() {
		return deliveryfrom;
	}
	public void setDeliveryfrom(String deliveryfrom) {
		this.deliveryfrom = deliveryfrom;
	}
	public String getDeliveryto() {
		return deliveryto;
	}
	public void setDeliveryto(String deliveryto) {
		this.deliveryto = deliveryto;
	}
	
}
