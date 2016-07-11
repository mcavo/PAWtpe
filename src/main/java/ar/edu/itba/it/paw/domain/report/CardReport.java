package ar.edu.itba.it.paw.domain.report;

import java.io.Serializable;
import java.util.List;

import ar.edu.itba.it.paw.domain.restaurant.Restaurant;

public class CardReport implements Serializable{

	private Restaurant rest;
	private List<Card> cards;
	
	public CardReport(Restaurant rest, List<Card> cards) {
		this.rest = rest;
		this.cards = cards;
	}

	public Restaurant getRest() {
		return rest;
	}

	public List<Card> getCards() {
		return cards;
	}
	
	
}
