package ar.edu.itba.it.paw.domain.users;

import java.util.LinkedList;
import java.util.List;

import ar.edu.itba.it.paw.domain.report.CardReport;
import ar.edu.itba.it.paw.domain.restaurant.Restaurant;
import ar.edu.itba.it.paw.exceptions.DuplicateDishException;
import ar.edu.itba.it.paw.exceptions.InvalidPriceException;
import ar.edu.itba.it.paw.exceptions.InvalidSectionName;
import ar.edu.itba.it.paw.exceptions.NoManagersAvailableException;
import ar.edu.itba.it.paw.exceptions.NoRestaurantException;

public interface ManagerRepositoryType {

	public void addDish(Restaurant rest, String section, String dish, int price, String desc) throws NoRestaurantException, InvalidPriceException, DuplicateDishException, InvalidSectionName;
	public Restaurant getRestaurant(User manager) throws NoRestaurantException;
	public boolean existsDish(String dish);
	public List<Credential> getManagersAvailables() throws NoManagersAvailableException;
	public void setManager(User manager);
	public LinkedList<CardReport> getReport(User manager);
}
