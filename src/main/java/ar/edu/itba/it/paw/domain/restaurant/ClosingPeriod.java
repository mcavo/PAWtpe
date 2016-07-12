package ar.edu.itba.it.paw.domain.restaurant;

import java.io.Serializable;
import java.util.Date;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.ocpsoft.prettytime.PrettyTime;

import ar.edu.itba.it.paw.domain.common.PersistentEntity;
import ar.edu.itba.it.paw.services.StringService;

@Entity
@Table(name = "periodoClausura")
public class ClosingPeriod extends PersistentEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name = "desde")
	private Date from;
	
	@Column(name = "hasta")
	private Date to;
	
	@Column(name = "razon")
	private String description;
	
	@ManyToOne
	@JoinColumn(name ="restid")
	private Restaurant restaurant;
	
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
	
	public String getToString() {
		PrettyTime prettyTime = new PrettyTime(new Locale("es"));
		return prettyTime.format(this.getTo());
	}
	
	public String getFromString() {
		PrettyTime prettyTime = new PrettyTime(new Locale("es"));
		return prettyTime.format(this.getFrom());
	}
	
	public void setDescription(String description) {
		StringService.validateMaximumLength(description, 500);
		this.description = description;
	}
	
}
