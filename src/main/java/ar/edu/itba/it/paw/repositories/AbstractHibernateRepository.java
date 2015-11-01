package ar.edu.itba.it.paw.repositories;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class AbstractHibernateRepository {
	private final SessionFactory sessionFactory;

		public AbstractHibernateRepository(SessionFactory sessionFactory) {
			this.sessionFactory = sessionFactory;
		}

		public <T> T get(Class<T> type, Serializable id) {
			Session session = getSession();
			Transaction tx = session.beginTransaction();
			T obj = (T) getSession().get(type, id);
			tx.commit();
			return obj;
		}

		@SuppressWarnings("unchecked")
		public <T> List<T> find(String hql, Object... params) {
			Session session = getSession();
			Transaction tx = sessionFactory.getCurrentSession().beginTransaction();
			Query query = session.createQuery(hql);
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
			List<T> list = query.list();
			tx.commit();
			return list;
		}

		protected org.hibernate.Session getSession() {
			return sessionFactory.getCurrentSession();
		}

		public Serializable save(Object o) {
			Session session = getSession();
			Transaction tx = session.beginTransaction();
			Serializable obj = session.save(o);
			tx.commit();
			return obj;
		}
		
		public void update(Object o){
			Session session = getSession();
			Transaction tx = session.getTransaction();
			session.update(o);
			tx.commit();
		}

}