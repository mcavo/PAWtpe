package ar.edu.itba.it.paw.domain.restaurant;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CollectionOfElements;
import org.joda.time.LocalTime;

import ar.edu.itba.it.paw.domain.address.Address;
import ar.edu.itba.it.paw.domain.address.Neighborhood;
import ar.edu.itba.it.paw.domain.common.PersistentEntity;
import ar.edu.itba.it.paw.domain.users.User;

@Entity
@Table(name = "restaurante")
public class Restaurant extends PersistentEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public final static int DESCRIPTIO_MAX_LENGTH = 500;
	public final static int NAME_MAX_LENGTH = 30;
	public final static int NO_EMPTY = 0;
	
	@OneToMany
	@JoinTable(name="gerente", joinColumns=@JoinColumn(name="restid"), inverseJoinColumns=@JoinColumn(name="userid"))
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
	
	@OneToMany(cascade=CascadeType.ALL)
	@MapKey(name="restid")
	private List<Calification> califications;
	
	@OneToMany
	@JoinColumn(name="restid")
	List<Dish> menu;
	
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
	
	public Restaurant(int id, String name, Float minimumPurchase, String startService, String endService, Address address, List<String> typeOfFood, List<Dish> menu, Double cost, String delfrom,String delto, Set<Neighborhood> deliveryneigh) {
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
	
	public Restaurant(int id, String name, Float minimumPurchase, String startService, String endService, Address address, List<String> typeOfFood, Double cost, String delfrom,String delto, Set<Neighborhood> deliveryneigh) {
		this.setName(name);
		this.setMinamount(minimumPurchase);
		this.setFrom(startService);
		this.setTo(endService);
		this.setAddress(address);
		this.setTypesOfFood(typeOfFood);
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

	public void setDelamount(double cost) {
		this.delamount = cost;
	}

	public void setCostoenvio(Double delamount) {
		this.delamount = delamount;
	}

	public String getName() {
		return name;
	}

	public void setName(String nombre) {
		this.name = nombre;
	}

	public void setManagers(Set<User> managers) {
		this.managers = managers;
	}

	public void addManager(User manager) {
		//TODO: ver el tema de las validaciones
		this.managers.add(manager);
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
	
	public List<Dish> getMenu() {
		return menu;
	}

	public void setMenu(List<Dish> menu) {
		this.menu = menu;
	}

	public double getScore() {
		double s = 0;
		for (Calification calification : califications) {
			s+=calification.getPuntaje();
		}
		if(califications.isEmpty())
			return s;
		s = s/((double) califications.size());
		return Math.round(s*100)/100.0;
	}

	public Timestamp getRegis() {
		return regis;
	}

	public void setRegis(Timestamp regis) {
		this.regis = regis;
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

	public void addCalification(Calification q) {
		this.califications.add(q);
		
	}

	public boolean hasCalificationByUser(User user) {
		for (Calification calification : califications) {
			if(calification.getUser().equals(user)){
				return true;
			}
		}
		return false;
	}
	
	public List<List<Dish>> getMenuSections() {
		Map<String, List<Dish>> sections = new HashMap<String, List<Dish>>();
		String name;
		for (Dish d : menu) {
			name = d.getSection();
			if (sections.containsKey(name)) {
				sections.get(name).add(d);
			} else {
				List<Dish> l = new LinkedList<Dish>();
				l.add(d);
				sections.put(name, l);
			}
		}
		return new LinkedList<List<Dish>>(sections.values());
	}
	
	public void addDish(Dish d) {
		if (d != null) {
			menu.add(d);
		}
	}

	// Show name + direccion
	public String getString() {
		return name + " - " + address.toString();
	}

}