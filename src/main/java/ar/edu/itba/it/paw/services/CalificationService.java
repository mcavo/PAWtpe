package ar.edu.itba.it.paw.services;

import java.util.HashMap;

import ar.edu.itba.it.paw.models.Calification;
import ar.edu.itba.it.paw.models.Restaurant;

public interface CalificationService {

	public boolean canQualify(Restaurant r, int usrId);
	
	public void addCalification(int usrId, Restaurant rest, String stars, String comments);
	
	public HashMap<Integer, Calification> getCalifications(Restaurant restaurant);
}
