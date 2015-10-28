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
	private String nombre;

	@Transient
	private LinkedList<User> managers;
	
	@Column(name = "montomin")
	private double montomin;
	
	@Column(name = "desde")
	private Float desde;
	
	@Column(name = "hasta")
	private Float hasta;
	
	@Transient
	private Address address;
	
	@Transient
	private List<String> typesOfFood;
	
	@Column(name = "descripcion")
	private String descripcion;
	
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
	private Float costoenvio;
	
	@Column(name = "dirid")
	private int dirid;
	
	//Only for javabean
	public Restaurant() {
		
	}
	
	public Restaurant(int id, String name, double minimumPurchase, Float startService, Float endService, Address address, List<String> typeOfFood, Menu menu, Float cost) {
		// TODO Auto-generated constructor stub
		this.setNombre(name);
		this.setMontomin(minimumPurchase);
		this.setDesde(startService);
		this.setHasta(endService);
		this.setAddress(address);
		this.setTypesOfFood(typeOfFood);
		this.setMenu(menu);
		this.id = id;
		this.setCostoenvio(cost);
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/*public void setNombre(String nombre) {
		this.name = name;
	}*/
	
	public LinkedList<User> getManager() {
		if(managers==null) {
			
		}
		return managers;
	}

	public void setManagers(LinkedList<User> managers) {
		this.managers = managers;
	}

	public double getMontomin() {
		return montomin;
	}

	public void setMontomin(double montomin) {
		this.montomin = montomin;
	}

	public Float getDesde() {
		return desde;
	}

	public void setDesde(Float desde) {
		this.desde = desde;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Float getHasta() {
		return hasta;
	}

	public void setHasta(Float hasta) {
		this.hasta = hasta;
	}

	public List<String> getTypesOfFood() {
		return typesOfFood;
	}

	public void setTypesOfFood(List<String> typesOfFood) {
		this.typesOfFood = typesOfFood;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public Timestamp getRegis() {
		return regis;
	}

	public void setRegis(Timestamp regis) {
		this.regis = regis;
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

	public float getCostoenvio() {
		return costoenvio;
	}

	public void setCostoenvio(float costoenvio) {
		this.costoenvio = costoenvio;
	}
}
