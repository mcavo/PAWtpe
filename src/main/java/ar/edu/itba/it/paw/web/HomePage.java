package ar.edu.itba.it.paw.web;

import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.domain.restaurant.RestRepo;
import ar.edu.itba.it.paw.web.base.BasePage;

public class HomePage extends BasePage {

	private static final long serialVersionUID = 1L;

	@SpringBean
	private RestRepo restaurantRepo;

	public HomePage() {

	}
}
