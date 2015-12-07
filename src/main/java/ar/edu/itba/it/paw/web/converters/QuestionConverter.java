package ar.edu.itba.it.paw.web.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.domain.users.Question;
import ar.edu.itba.it.paw.domain.users.QuestionsRepository;

@Component
public class QuestionConverter implements Converter<String, Question>{
	
	private QuestionsRepository questions;
	
	@Autowired
	public QuestionConverter(QuestionsRepository questions) {
		this.questions = questions;
	}
	
	public Question convert(String arg0) {
		return questions.getById(Integer.valueOf(arg0)); 
	}
}
