package ar.edu.itba.it.paw.Repositories;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Session;

public class AbstractHibernateRepository {
	private final SessionFactory sessionFactory;

		public AbstractHibernateRepository(SessionFactory sessionFactory) {
			this.sessionFactory = sessionFactory;
		}

		public <T> T get(Class<T> type, Serializable id) {
			return (T) getSession().get(type, id);
		}

		@SuppressWarnings("unchecked")
		public <T> List<T> find(String hql, Object... params) {
			Session session = getSession();

			Query query = session.createQuery(hql);
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
			List<T> list = query.list();
			return list;
		}

		protected org.hibernate.Session getSession() {
			return sessionFactory.getCurrentSession();
		}

		public Serializable save(Object o) {
			return getSession().save(o);
		}

}
