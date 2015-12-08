package ar.edu.itba.it.paw.domain.restaurant;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CollectionOfElements;
import org.joda.time.LocalTime;

import ar.edu.itba.it.paw.domain.address.Address;
import ar.edu.itba.it.paw.domain.address.Neighborhood;
import ar.edu.itba.it.paw.domain.common.PersistentEntity;
import ar.edu.itba.it.paw.domain.users.User;

@Entity
@Table(name = "restaurante")
public class Restaurant extends PersistentEntity {
	//TODO: esto esta haciendo algo?
	@OneToMany
	@JoinColumn(name="gerid")
	private Set<User> managers;
	
	@ManyToMany
	@JoinTable(name="delivery", joinColumns=@JoinColumn(name="restid"), inverseJoinColumns=@JoinColumn(name="barrioid"))
	private Set<Neighborhood> deliveryneigh;
	
	@Column(name = "nombre")
	private String name;
	
	@Column(name = "montomin")
	private Float montomin;
	
	@Column(name = "desde")
	private String from;
	
	@Column(name = "hasta")
	private String to;
	
	@OneToOne
	@JoinColumn(name="dirid")
	private Address address;
	
	@CollectionOfElements(targetElement = java.lang.String.class)
	@JoinTable(name = "tipos", joinColumns = @JoinColumn(name = "restid"))
	@Column(name="tipo")
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
	private String deliveryfrom;
	
	@Column(name="deliveryhasta")
	private String deliveryto;
	
	@Column(name = "regis")
	private Timestamp regis;
	
	@Column(name = "costoenvio")
	private Double delamount;

	//Only for javabean
	Restaurant() {
		
	}
	
	public Restaurant(int id, String name, Float minimumPurchase, String startService, String endService, Address address, List<String> typeOfFood, Menu menu, Double cost, String delfrom,String delto, Set<Neighborhood> deliveryneigh) {
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

	public String getDeliveryfrom() {
		return deliveryfrom;
	}

	public void setDeliveryfrom(String deliveryfrom) {
		this.deliveryfrom = deliveryfrom;
	}

	public String getDeliveryto() {
		return deliveryto;
	}

	public void setDeliveryto(String deliveryto) {
		this.deliveryto = deliveryto;
	}

	public Set<User> getManagers() {
		return managers;
	}

	public HashMap<Integer, Calification> getCalifications() {
		return califications;
	}

	public void setDelamount(double cost) {
		this.delamount = cost;
	}

	public void setCostoenvio(Double delamount) {
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

	public String getFrom() {
		return from;
	}

	public void setFrom(String desde) {
		this.from = desde;
	}

	public void setDesde(String desde) {
		this.from = desde;
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String hasta) {
		this.to = hasta;
	}

	public void setHasta(String hasta) {
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

	
	public Double getDelamount() {
		return delamount;
	}
	
	public void setNombre(String nombre) {
		this.name = nombre;
	}
	
	public boolean getIsClosed(){
		LocalTime dfrom = new LocalTime(this.from);
		LocalTime dto = new LocalTime(this.to);
		LocalTime now = new LocalTime();
		if(dfrom.isAfter(dto)) {
			return !((now.isAfter(new LocalTime("00:00")) && now.isBefore(dto)) || (now.isAfter(dfrom) && now.isBefore(new LocalTime("23:59"))));
		}
		return !(now.isAfter(dfrom) && now.isBefore(dto));
	}
	
	public boolean getHasNotDelivery(){
		LocalTime dfrom = new LocalTime(this.deliveryfrom);
		LocalTime dto = new LocalTime(this.deliveryto);
		LocalTime now = new LocalTime();
		if(dfrom.isAfter(dto)) {
			return !((now.isAfter(new LocalTime("00:00")) && now.isBefore(dto)) || (now.isAfter(dfrom) && now.isBefore(new LocalTime("23:59"))));
		}
		return !(now.isAfter(dfrom) && now.isBefore(dto));
	}
}