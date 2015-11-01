package ar.edu.itba.it.paw.models;

//no esta mapeado porque no tiene id e hibernate no lo acepta
public class Calification {
	
	private Integer puntaje;
	private String descripcion;
	private int userId;

	public Calification(){
		
	}
	
	public Calification(int stars, String comment) {
		this.setPuntaje(stars);
		this.setDescripcion(comment);
	}

	public double getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}
