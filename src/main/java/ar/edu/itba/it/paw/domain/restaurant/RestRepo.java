package ar.edu.itba.it.paw.domain.restaurant;

import java.util.List;

import ar.edu.itba.it.paw.domain.users.User;

public interface RestRepo {

	public List<Restaurant> getAll();
	public boolean userCanOrder(User user, Restaurant rest);
	public List<Restaurant> getLastWeekAdded();
}
