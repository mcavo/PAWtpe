package ar.edu.itba.it.paw.repositories;

import java.util.HashMap;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.models.Calification;
import ar.edu.itba.it.paw.models.Restaurant;
import ar.edu.itba.it.paw.models.User;

@Repository
public class CalificationRepository extends AbstractHibernateRepository{
	
	@Autowired
	public CalificationRepository(SessionFactory sessionFactory) {
		super(sessionFactory);		
	}

	public void addCalification(User user, Restaurant rest, String stars, String comment){
		int restId = rest.getId();
		//int userId = Integer.valueOf(usrId);
		if(user.getId() == 0 || restId == 0){
			//app error
			return;
		}
		int rate = Integer.valueOf(stars);
		if(rate < 0 || rate > 5){
			//app error
			return;
		}
		if(comment.isEmpty()){
			//tirar excepcion de comentario vacio
			return;
		}
		Session session=null;
	    try 
	    {
		    Session sessionSQL = super.getSession();
		    Transaction tx = sessionSQL.beginTransaction();
		    SQLQuery query = (SQLQuery) sessionSQL.createSQLQuery("insert into calificacion (userid, restid, descripcion, puntaje) VALUES (?, ?, ?, ?);");
		    query.setParameter(0, user.getId()); 
		    query.setParameter(1, restId);
		    query.setParameter(2, comment);
		    query.setParameter(3, rate);
		    query.executeUpdate();
		    tx.commit();
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	    finally
	    {
	        if(session !=null && session.isOpen())
	        {
	          session.close();
	          session=null;
	        }
	    }
	}
	
	@SuppressWarnings("unchecked")
	public HashMap<Integer, Calification> getCalifications(Restaurant restaurant) {
		List<Calification> califications = null;
		Session session=null;
	    try 
	    {
		    Session sessionSQL = super.getSession();
		    Transaction tx = sessionSQL.beginTransaction();
		    SQLQuery query = (SQLQuery) sessionSQL.createSQLQuery("SELECT * from calificacion where restid = ?").setParameter(0, restaurant.getId()); 
		    query.addScalar("userId", Hibernate.INTEGER);
		    query.addScalar("descripcion", Hibernate.STRING);
		    query.addScalar("puntaje", Hibernate.INTEGER);
		    califications = query.setResultTransformer(Transformers.aliasToBean(Calification.class)).list();
		    tx.commit();
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	    finally
	    {
	        if(session !=null && session.isOpen())
	        {
	          session.close();
	          session=null;
	        }
	    }
		int userid;
		String descripcion;
		int puntaje;
		Calification calif;
		HashMap<Integer, Calification> qMap = new HashMap<Integer, Calification>();
		for (Calification q : califications) {
			userid = q.getUserId();
			descripcion = q.getDescripcion();
			puntaje = (int) q.getPuntaje();
			calif = new Calification(puntaje, descripcion);
			qMap.put(userid,calif);
		}
		return qMap;
	}
}
