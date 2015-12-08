package ar.edu.itba.it.paw.domain.restaurant;

import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import ar.edu.itba.it.paw.domain.common.PersistentEntity;
import ar.edu.itba.it.paw.domain.users.User;

@Entity
@Table(name = "pedido")
public class Order extends PersistentEntity {
	
	@Transient
	private Map<Dish,Integer> ordlist;
	
	@ManyToOne
	@JoinColumn(name = "userid")
	private User user;

	@ManyToOne
	@JoinColumn(name="restid")
	private Restaurant rest;
	
	@Column(name = "estado")
	private Integer status;
	
	Order(){
		
	}
	
	public Order (Restaurant rest, User user,int status) {
		this.rest=rest;
		this.user=user;
		this.status=status;
	}

	public Map<Dish, Integer> getOrdlist() {
		return ordlist;
	}

	public void setOrdlist(Map<Dish, Integer> ordlist) {
		this.ordlist = ordlist;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
	
	public double getTotal() {
		double total = (double)0;
		for(Entry<Dish,Integer> set : ordlist.entrySet()) {
			total+=set.getValue()*set.getKey().getPrice();
		}
		return total;
	}
}
