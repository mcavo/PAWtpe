package ar.edu.itba.it.paw.domain.users;

import java.util.List;

public interface QuestionRepositoryType {
	public List<Question> getQuestions();
	public Question getById(Integer id);
}
