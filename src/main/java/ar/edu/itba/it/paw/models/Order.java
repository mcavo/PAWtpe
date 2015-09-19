package ar.edu.itba.it.paw.models;

import java.util.Map;

public class Order {
	
	private Map<Dish,Integer> ordlist;
	private User user;
	private Restaurant rest;
	private int status;
	
	public Order (Restaurant rest,Map <Dish,Integer> ordlist,User user,int status) {
		this.rest=rest;
		this.ordlist=ordlist;
		this.user=user;
		this.status=status;
	}

	public Map<Dish, Integer> getOrdlist() {
		return ordlist;
	}

	public void setOrdlist(Map<Dish, Integer> ordlist) {
		this.ordlist = ordlist;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Restaurant getRest() {
		return rest;
	}

	public void setRest(Restaurant rest) {
		this.rest = rest;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}