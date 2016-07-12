package ar.edu.itba.it.paw.domain.report;

import java.io.Serializable;

import ar.edu.itba.it.paw.domain.address.Neighborhood;

public class Card implements Serializable{

	private Neighborhood neighbourhood;
	private int cant;
	
	public Card(Neighborhood neighborhood, int cant) {
		this.neighbourhood = neighborhood;
		this.cant = cant;
	}

	public Neighborhood getNeighbourhood() {
		return neighbourhood;
	}

	public int getCant() {
		return cant;
	}
	
	
}
