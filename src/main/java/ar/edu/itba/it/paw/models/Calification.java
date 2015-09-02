package ar.edu.itba.it.paw.models;

public class Calification {
	private double starts;
	private String comment;
	
	public Calification(double starts, String comment) {
		this.setStarts(starts);
		this.setComment(comment);
	}

	public double getStarts() {
		return starts;
	}

	public void setStarts(double starts) {
		this.starts = starts;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
