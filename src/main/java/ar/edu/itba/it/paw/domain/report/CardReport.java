package ar.edu.itba.it.paw.domain.report;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import ar.edu.itba.it.paw.domain.restaurant.Restaurant;

public class CardReport implements Serializable{

	private Restaurant rest;
	private List<Card> cards;
	private Date from;
	private Date to;
	
	public CardReport(Restaurant rest, List<Card> cards, Date from, Date to) {
		this.rest = rest;
		this.cards = cards;
		this.from = from;
		this.to = to;
	}

	public Restaurant getRest() {
		return rest;
	}

	public List<Card> getCards() {
		return cards;
	}
	
	public Date getFrom(){
		return this.from;
	}
	
	public Date getTo(){
		return this.to;
	}
}
