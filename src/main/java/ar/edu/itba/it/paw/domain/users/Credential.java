package ar.edu.itba.it.paw.domain.users;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import ar.edu.itba.it.paw.domain.common.PersistentEntity;
import ar.edu.itba.it.paw.services.StringService;
@Entity
@Table(name = "credencial")
public class Credential extends PersistentEntity implements Serializable {
	
	@Column(name = "rol")
	private String rol;
	
	@Column(name = "mail")
	private String mail;
	
	@Column(name = "psw")
	private String psw;
	
	Credential() {
		
	}
	
	public Credential(String rol, String mail, String psw) {
		setRol(rol);
		setMail(mail);
		setPsw(psw);
	}
	
	public Credential(int id, String rol, String mail) {
		super.setId(id);
		setRol(rol);
		setMail(mail);
	}
	
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		validateRol(rol);
		this.rol = rol;
	}
	
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		StringService.validateMail(mail);
		this.mail = mail;
	}
	
	public void setPsw(String psw) {
		StringService.validateMaximumLength(psw, 16);
		this.psw=psw;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.getId();
		result = prime * result + ((mail == null) ? 0 : mail.hashCode());
		result = prime * result + ((rol == null) ? 0 : rol.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Credential other = (Credential) obj;
		if (this.getId() != other.getId())
			return false;
		if (mail == null) {
			if (other.mail != null)
				return false;
		} else if (!mail.equals(other.mail))
			return false;
		if (rol == null) {
			if (other.rol != null)
				return false;
		} else if (!rol.equals(other.rol))
			return false;
		return true;
	}
	
	private void validateRol(String rol) {
		if (!(rol.equals("usuario") || rol.equals("manager") || rol.equals("admin"))) {
			throw new IllegalArgumentException("invalid rol exception");
		}
	}
}
