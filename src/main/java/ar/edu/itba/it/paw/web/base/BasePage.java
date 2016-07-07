package ar.edu.itba.it.paw.web.base;

import org.apache.wicket.markup.html.WebPage;

public class BasePage extends WebPage {
	private static final long serialVersionUID = 1L;

	public BasePage() {
		add(new HeaderPanel("headerPanel"));
	}
}