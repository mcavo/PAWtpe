package ar.edu.itba.it.paw.exceptions;

import ar.edu.itba.it.paw.domain.users.User;

public class NoRestaurantException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User manager;
	
	public NoRestaurantException(User manager) {
		this.setManager(manager);
	}

	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}
}
