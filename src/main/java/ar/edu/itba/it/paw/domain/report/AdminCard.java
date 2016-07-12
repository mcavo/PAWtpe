package ar.edu.itba.it.paw.domain.report;

public class AdminCard {

	private String restName;
	private int cant;
	
	public AdminCard(String restName, int cant) {
		this.restName = restName;
		this.cant = cant;
	}

	public String getRestName() {
		return restName;
	}

	public int getCant() {
		return cant;
	}
	
	
}
