package ar.edu.itba.it.paw;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConnection {

	private static HibernateConnection conn;
	private static SessionFactory sessionFactory;
	
	private HibernateConnection(){}
	
	public static HibernateConnection getInstance(){
		if(conn == null){
			conn = new HibernateConnection(); 
			Configuration conf =  new Configuration().configure("/hibernate.cfg.xml");
			sessionFactory =  conf.buildSessionFactory();
		}
		return conn;
	}
	
	public SessionFactory getSessionFactory(){
		return this.sessionFactory;
	}
}
