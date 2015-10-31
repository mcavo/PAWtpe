package ar.edu.itba.it.paw.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

import ar.edu.itba.it.paw.DBManager;
import ar.edu.itba.it.paw.models.Calification;
import ar.edu.itba.it.paw.models.Restaurant;

@Repository
public class CalificationRepository extends AbstractHibernateRepository{

	@Autowired
	public CalificationRepository(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public void addCalification(int userId, int restId, int rate, String comment){
		Session session=null;
	    try 
	    {
		    Session sessionSQL = super.getSession();
		    Transaction tx = sessionSQL.beginTransaction();
		    SQLQuery query = (SQLQuery) sessionSQL.createSQLQuery("insert into calificacion (userid, restid, descripcion, puntaje) VALUES (?, ?, ?, ?);");
		    query.setParameter(0, userId); 
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
		/*try {
			Connection conn = DBManager.getInstance().getConnection();
			String sql = "insert into Calificacion (userid, restid, descripcion, puntaje) VALUES (?, ?, ?, ?);";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			pstmt.setInt(2, restId);
			pstmt.setString(3, comment);
			pstmt.setInt(4, rate);
			
			pstmt.execute();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
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
