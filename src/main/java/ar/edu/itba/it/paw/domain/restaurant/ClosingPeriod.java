package ar.edu.itba.it.paw.domain.restaurant;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ar.edu.itba.it.paw.services.StringService;

@Entity
@Table(name = "periodoClausura")
public class ClosingPeriod {
	
	@Column(name = "desde")
	private Date from;
	
	@Column(name = "hasta")
	private Date to;
	
	@Column(name = "razon")
	private String description;
	
	@ManyToOne
	@JoinColumn(name ="restid")
	private Restaurant restaurant;
	
	@Id
	@Column(name = "id")
	private int id;
	
	public ClosingPeriod() {
	}
	
	public ClosingPeriod(Date from, Date to, Restaurant restaurant) {
		this.from = from;
		this.to = to;
		this.restaurant = restaurant;
	}

	public Date getFrom() {
		return from;
	}

	public Date getTo() {
		return to;
	}

	public String getDescription() {
		return description;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}
	
	public int getId() {
		return id;
	}
	
	public void setDescription(String description) {
		StringService.validateMaximumLength(description, 500);
		this.description = description;
	}
	
}
