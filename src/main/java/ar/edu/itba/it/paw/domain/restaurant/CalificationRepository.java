package ar.edu.itba.it.paw.domain.restaurant;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.domain.common.AbstractHibernateRepository;

@Repository
public class CalificationRepository extends AbstractHibernateRepository{

	@Autowired
	public CalificationRepository(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

}
