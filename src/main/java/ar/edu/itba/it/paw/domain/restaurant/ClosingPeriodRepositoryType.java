package ar.edu.itba.it.paw.domain.restaurant;

import java.util.List;

public interface ClosingPeriodRepositoryType {

	public List<ClosingPeriod> getClosingHistorial(Restaurant restaurant);
	public void add(ClosingPeriod cp);
}
