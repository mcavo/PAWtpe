package ar.edu.itba.it.paw.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


public class Calification {
	
	private Integer stars;
	
	private String comment;
	
	public Calification(){
		
	}
	
	public Calification(int stars, String comment) {
		this.setStars(stars);
		this.setComment(comment);
	}

	public double getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
