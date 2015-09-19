package ar.edu.itba.it.paw.models;

public class Calification {
	private int stars;
	private String comment;
	
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
