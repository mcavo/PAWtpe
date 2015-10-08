package ar.edu.itba.it.paw.DAO;

import java.util.HashMap;

import ar.edu.itba.it.paw.models.Calification;

public interface CalificationDAO {

	public void addCalification(int userId, int restId, int rate, String comment);
	
	public HashMap<Integer, Calification> getCalificationsByRestId(int restId);
	
}
