package ar.edu.itba.it.paw.web;

import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.domain.restaurant.RestRepo;
import ar.edu.itba.it.paw.domain.restaurant.Restaurant;
import ar.edu.itba.it.paw.web.base.BasePage;

public class HomePage extends BasePage {

	private static final long serialVersionUID = 1L;

	@SpringBean
	private RestRepo restaurantRepo;

	public HomePage() {

		boolean isSignIn = BaseSession.get().isSignedIn();
		if(!isSignIn){
			Link<Image> signupLink = new Link<Image>("signupLink") {
	
				@Override
				public void onClick() {
					//setResponsePage(SignUpPage.class);
				}
			};
			add(signupLink.setVisible(true));
		}
	}
}
