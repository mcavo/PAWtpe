package ar.edu.itba.it.paw.domain.common;

import java.io.Serializable;

public class Message implements Serializable {
	String type;
	String text;
	
	public Message(String type, String text) {
		this.type = type;
		this.text = text;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
