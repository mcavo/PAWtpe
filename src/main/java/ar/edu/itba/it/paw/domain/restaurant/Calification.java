package ar.edu.itba.it.paw.domain.restaurant;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ar.edu.itba.it.paw.domain.common.PersistentEntity;
import ar.edu.itba.it.paw.domain.users.User;
import ar.edu.itba.it.paw.services.NumberService;
import ar.edu.itba.it.paw.services.StringService;

@Entity
@Table(name = "calificacion")
public class Calification extends PersistentEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column
	private Integer puntaje;
	
	@Column
	private String descripcion;
	
	@ManyToOne
	@JoinColumn(name ="userid")
	private User user;
	
	@ManyToOne
	@JoinColumn(name ="restid")
	private Restaurant restaurant;
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public Calification(){
		
	}
	
	public Calification(int stars, String comment) {
		this.setPuntaje(stars);
		this.setDescripcion(comment);
	}

	public double getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		NumberService.validateMax(puntaje, 5);
		NumberService.validateMin(puntaje, 0);
		this.puntaje = puntaje;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		StringService.validateMaximumLength(descripcion, 500);
		this.descripcion = descripcion;
	}
}
