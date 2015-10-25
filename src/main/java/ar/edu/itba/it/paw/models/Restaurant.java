package ar.edu.itba.it.paw.models;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "restaurante")
public class Restaurant {
	
	@Column(name = "nombre")
	private String name;

	@Transient
	private LinkedList<User> managers;
	
	@Column(name = "montomin")
	private double minimumPurchase;
	
	@Column(name = "desde")
	private Float startService;
	
	@Column(name = "hasta")
	private Float endService;
	
	@Transient
	private Address address;
	
	@Transient
	private List<String> typesOfFood;
	
	@Column(name = "descripcion")
	private String description;
	
	@Transient
	private HashMap<Integer, Calification> califications;
	
	@Transient
	private Menu menu;
	
	@Transient
	private double score = (double)0;
	
	//@Temporal(TemporalType.DATE)
	@Transient
	private Date startDate;
	
	@Column(name = "regis")
	private Timestamp regis;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "costoenvio")
	private Float cost;
	
	@Column(name = "dirid")
	private int dirid;
	
	//Only for javabean
	public Restaurant() {
		
	}
	
	public Restaurant(int id, String name, double minimumPurchase, Float startService, Float endService, Address address, List<String> typeOfFood, Menu menu, Float cost) {
		// TODO Auto-generated constructor stub
		this.setName(name);
		this.setMinimumPurchase(minimumPurchase);
		this.setStartService(startService);
		this.setEndService(endService);
		this.setAddress(address);
		this.setTypesOfFood(typeOfFood);
		this.setMenu(menu);
		this.id = id;
		this.setCost(cost);
	}
	
	private void calculateScore() {
		if(califications==null || califications.isEmpty()) {
			this.score = (double) 0;
			return;
		}
			
		double saux = (double) 0;
		for (Entry<Integer,Calification> set : califications.entrySet()) {
			saux += set.getValue().getStars();
		}
		saux/=califications.size();
		score=saux;
	}


	public void setCalifications(HashMap<Integer, Calification> qMap) {
		this.califications = qMap;
		calculateScore();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LinkedList<User> getManager() {
		if(managers==null) {
			
		}
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

	public Float getStartService() {
		return startService;
	}

	public void setStartService(Float startService) {
		this.startService = startService;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Float getEndService() {
		return endService;
	}

	public void setEndService(Float endService) {
		this.endService = endService;
	}

	public List<String> getTypesOfFood() {
		return typesOfFood;
	}

	public void setTypesOfFood(List<String> typesOfFood) {
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public HashMap<Integer, Calification> getQualifications() {
		return this.califications;
	}

	public void setId(int id){
		this.id = id;
	}
	
	public int getId() {
		// TODO Auto-generated method stub
		return this.id;
	}
	
	public int getCountComments(){
		if(califications==null)
			return 0;
		return califications.size();
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}
}
