package ar.edu.itba.it.paw.models;

import java.sql.Time;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;

public class Restaurant {
	private String name;
	private LinkedList<User> managers;
	private double minimumPurchase;
	private Time startService;
	private Time endService;
	private Address address;
	private LinkedList<String> typesOfFood;
	private String description;
	private HashMap<User, Calification> califications;
	private Menu menu;
	private double score;
	private LocalDate startDate;
	
	public Restaurant(String name, double minimumPurchase, Time startService, Time endService, Address address, LinkedList<String> typeOfFood, LinkedList<User>managers, Menu menu) {
		// TODO Auto-generated constructor stub
		this.setName(name);
		this.setManagers(managers);
		this.setMinimumPurchase(minimumPurchase);
		this.setStartService(startService);
		this.setEndService(endService);
		this.setAddress(address);
		this.setTypesOfFood(typeOfFood);
		this.califications = new HashMap<User, Calification> ();
		this.setMenu(menu);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LinkedList<User> getManagers() {
		return managers;
	}

	public void setManagers(LinkedList<User> managers) {
		this.managers = managers;
	}

	public double getMinimumPurchase() {
		return minimumPurchase;
	}

	public void setMinimumPurchase(double minimumPurchase) {
		this.minimumPurchase = minimumPurchase;
	}

	public Time getStartService() {
		return startService;
	}

	public void setStartService(Time startService) {
		this.startService = startService;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Time getEndService() {
		return endService;
	}

	public void setEndService(Time endService) {
		this.endService = endService;
	}

	public LinkedList<String> getTypesOfFood() {
		return typesOfFood;
	}

	public void setTypesOfFood(LinkedList<String> typesOfFood) {
		this.typesOfFood = typesOfFood;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public HashMap<User, Calification> getQualifications() {
		return this.califications;
	}

}
