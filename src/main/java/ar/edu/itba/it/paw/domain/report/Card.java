package ar.edu.itba.it.paw.domain.report;

import java.io.Serializable;

import ar.edu.itba.it.paw.domain.address.Neighborhood;

public class Card implements Serializable{

	private Neighborhood neighbourhoodId;
	private int cant;
	
	public Card(Neighborhood neighborhood, int cant) {
		this.neighbourhoodId = neighborhood;
		this.cant = cant;
	}

	public Neighborhood getNeighbourhood() {
		return neighbourhoodId;
	}

	public int getCant() {
		return cant;
	}
	
	
}
