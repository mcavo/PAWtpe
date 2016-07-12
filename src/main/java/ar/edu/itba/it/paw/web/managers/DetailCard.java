package ar.edu.itba.it.paw.web.managers;

public class DetailCard {

	private String day;
	private String hour;
	private int amount;
	
	public DetailCard(String day, String hour, int amount) {
		this.day = day;
		this.hour = hour;
		this.amount = amount;
	}

	public String getDay() {
		return day;
	}

	public String getHour() {
		return hour;
	}

	public int getAmount() {
		return amount;
	}
	
	
}
