package ar.edu.itba.it.paw.domain.users;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "preguntas")
public class Question implements Serializable {

	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "pregunta", unique = true)
	private String question;

	Question() {
	}

	// Only to use with javabean
	public Question(String question) {
		this.question = question;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

}
