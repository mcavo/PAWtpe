package ar.edu.itba.it.paw.domain.users;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.domain.common.AbstractHibernateRepository;

@Repository
public class QuestionsRepository extends AbstractHibernateRepository implements QuestionRepositoryType {

	@Autowired
	public QuestionsRepository(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public List<Question> getQuestions() {
		List<Question> l = find("FROM Question");
		return l;
	}
	
	public Question getById(Integer id) {
		return (Question) find("from Question where id = ?", id).get(0);
	}	
}
