package ar.edu.itba.it.paw;

import java.net.URI;

import org.hibernate.SessionFactory;
//import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
//import org.hibernate.service.ServiceRegistry;

public class HibernateConnection {

//	private static HibernateConnection conn;
//	private static SessionFactory sessionFactory;
//	
//	private HibernateConnection(){}
//	
//	public static HibernateConnection getInstance(){
//		if(conn == null){
//			conn = new HibernateConnection(); 
//			try {
//		        // Create the SessionFactory from hibernate.cfg.xml
//				URI dbUri = new URI(
//						"postgres://hubyihgpaouvuy:p59ImIPv_9CmNlMxbU-Cyn6tHF@ec2-54-235-162-144.compute-1.amazonaws.com:5432/dajenobv1kl0ho");
//
//		        String username = dbUri.getUserInfo().split(":")[0];
//				String password = dbUri.getUserInfo().split(":")[1];
//				String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
//				String ssloff = "?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
//				dbUrl += ssloff;
//				
//		        Configuration configuration = new Configuration();
//		        //configuration.configure("hibernate.cfg.xml");
//
//				configuration
//		                .setProperty("hibernate.connection.username", username);
//		        configuration
//		                .setProperty("hibernate.connection.password", password);
//		        configuration.setProperty("hibernate.connection.url", dbUrl);
//		        System.out.println("Hibernate Annotation Configuration loaded");
//
////		        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
////		                .applySettings(configuration.getProperties()).build();
////		        System.out.println("Hibernate Annotation serviceRegistry created");
////
////		        sessionFactory = configuration
////		                .buildSessionFactory(serviceRegistry);
//		    } catch (Throwable ex) {
//		        // Make sure you log the exception, as it might be swallowed
//		        System.err.println("Initial SessionFactory creation failed." + ex);
//		        throw new ExceptionInInitializerError(ex);
//		    }
//		}
//		return conn;
//	}
//	
//	@SuppressWarnings("static-access")
//	public SessionFactory getSessionFactory(){
//		return this.sessionFactory;
//	}
}
