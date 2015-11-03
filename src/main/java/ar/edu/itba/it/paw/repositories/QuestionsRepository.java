package ar.edu.itba.it.paw.repositories;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.models.Question;

@Repository
public class QuestionsRepository extends AbstractHibernateRepository {

	@Autowired
	public QuestionsRepository(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public List<Question> getQuestions() {
		List<Question> l = find("FROM Question");
		return l;
	}
}
