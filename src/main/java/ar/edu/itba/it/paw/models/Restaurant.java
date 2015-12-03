package ar.edu.itba.it.paw.models;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "restaurante")
public class Restaurant extends PersistentEntity {

	@OneToMany
	@JoinColumn(name="gerid")
	private Set<User> managers;
	//private LinkedList<User> managers;
	
	@ManyToMany
	private Set<Neighborhood> deliveryneigh;
	
	@Column(name = "nombre")
	private String name;
	
	@Column(name = "montomin")
	private Float montomin;
	
	@Column(name = "desde")
	private Float from;
	
	@Column(name = "hasta")
	private Float to;
	
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
	
	@Column(name="deliverydesde")
	private Float deliveryfrom;
	
	@Column(name="deliveryhasta")
	private Float deliveryto;
	
	//@Temporal(TemporalType.DATE)
	//@Transient
	//private Date startDate;
	
	@Column(name = "regis")
	private Timestamp regis;
	
	@Column(name = "costoenvio")
	private Float delamount;
	
	@Column(name = "dirid")
	private int dirid;

	//Only for javabean
	public Restaurant() {
		
	}
	
	public Restaurant(int id, String name, Float minimumPurchase, Float startService, Float endService, Address address, List<String> typeOfFood, Menu menu, Float cost, Float delfrom,Float delto, Set<Neighborhood> deliveryneigh) {
		this.setName(name);
		this.setMinamount(minimumPurchase);
		this.setFrom(startService);
		this.setTo(endService);
		this.setAddress(address);
		this.setTypesOfFood(typeOfFood);
		this.setMenu(menu);
		this.setId(id);;
		this.setDelamount(cost);
		this.setDeliveryfrom(delfrom);
		this.setDeliveryto(delto);
		this.setDeliveryneigh(deliveryneigh);
	}
	
	public Set<Neighborhood> getDeliveryneigh() {
		return deliveryneigh;
	}

	public void setDeliveryneigh(Set<Neighborhood> deliveryneigh) {
		this.deliveryneigh = deliveryneigh;
	}

	public Float getMontomin() {
		return montomin;
	}

	public void setMontomin(Float montomin) {
		this.montomin = montomin;
	}

	public Float getDeliveryfrom() {
		return deliveryfrom;
	}

	public void setDeliveryfrom(Float deliveryfrom) {
		this.deliveryfrom = deliveryfrom;
	}

	public Float getDeliveryto() {
		return deliveryto;
	}

	public void setDeliveryto(Float deliveryto) {
		this.deliveryto = deliveryto;
	}

	public Set<User> getManagers() {
		return managers;
	}

	public HashMap<Integer, Calification> getCalifications() {
		return califications;
	}

	public void setDelamount(Float delamount) {
		this.delamount = delamount;
	}

	public void setCostoenvio(Float delamount) {
		this.delamount = delamount;
	}
	
	private void calculateScore() {
		if(califications==null || califications.isEmpty()) {
			this.score = (double) 0;
			return;
		}
			
		double saux = (double) 0;
		for (Entry<Integer,Calification> set : califications.entrySet()) {
			saux += set.getValue().getPuntaje();
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

	public void setName(String nombre) {
		this.name = nombre;
	}
	
	public Set<User> getManager() {
		if(managers==null) {
			
		}
		return managers;
	}

	public void setManagers(Set<User> managers) {
		this.managers = managers;
	}

	public Float getMinamount() {
		return montomin;
	}

	public void setMinamount(Float montomin) {
		this.montomin = montomin;
	}

	public Float getFrom() {
		return from;
	}

	public void setFrom(Float desde) {
		this.from = desde;
	}

	public void setDesde(Float desde) {
		this.from = desde;
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Float getTo() {
		return to;
	}

	public void setTo(Float hasta) {
		this.to = hasta;
	}

	public void setHasta(Float hasta) {
		this.to = hasta;
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

	public void setDescription(String descripcion) {
		this.description = descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.description = descripcion;
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
	
	public int getCountComments(){
		if(califications==null)
			return 0;
		return califications.size();
	}

	
	public Float getDelamount() {
		return delamount;
	}

	public int getDirid() {
		return dirid;
	}

	public void setDirid(int dirid) {
		this.dirid = dirid;
	}
	
	public void setNombre(String nombre) {
		this.name = nombre;
	}
}