package ar.edu.itba.it.paw.domain.restaurant;

import java.util.List;

public interface RestRepo {

	public List<Restaurant> getAll();
	public List<Restaurant> getLastWeekAdded();
}
