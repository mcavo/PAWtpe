package ar.edu.itba.it.paw.DAO;

import ar.edu.itba.it.paw.models.Restaurant;

public interface ManagerDAO {

	public Restaurant getRestByManagerId(int managerId);
	
	public void addDish(int restId, String section, String dish, int price, String desc);
	
	public boolean existsSection(String section);
	
	public Restaurant getRestaurant(int managerId);
	
	public boolean existsDish(String dish);
	
	public void setManager(int userId, int restId);
}
