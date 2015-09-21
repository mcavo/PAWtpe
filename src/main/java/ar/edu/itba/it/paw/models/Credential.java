package ar.edu.itba.it.paw.models;

public class Credential {

	private int id;
	private String rol;
	private String mail;
	
	
	public Credential() {
		
	}
	public Credential(int id, String rol, String mail){
		this.id = id;
		this.rol = rol;
		this.mail = mail;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	
}
