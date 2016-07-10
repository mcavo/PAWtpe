package ar.edu.itba.it.paw.web.base;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.domain.users.User;
import ar.edu.itba.it.paw.domain.users.UserRepositoryType;
import ar.edu.itba.it.paw.web.BaseSession;
import ar.edu.itba.it.paw.web.HomePage;
import ar.edu.itba.it.paw.web.admin.AddManagerPage;
import ar.edu.itba.it.paw.web.authentication.LoginPage;
import ar.edu.itba.it.paw.web.authentication.SignupPage;
import ar.edu.itba.it.paw.web.managers.ShowOrdersPage;
import ar.edu.itba.it.paw.web.managers.dishes.AddDishPage;
import ar.edu.itba.it.paw.web.profile.ProfilePage;
import ar.edu.itba.it.paw.web.restaurant.register.RegisterRestaurantPage;

public class HeaderPanel extends Panel {

	@SpringBean
	private UserRepositoryType users;

	private User loggedUser;

	@SuppressWarnings({ "rawtypes", "serial" })
	public HeaderPanel(String id) {
		super(id);
		initialize();
		boolean isSignIn = BaseSession.get().isSignedIn();
		Link logoutLink = new Link("logoutLink") {

			@Override
			public void onClick() {
				BaseSession.get().invalidate();
				setResponsePage(HomePage.class);
			}

		};
		add(logoutLink.setVisible(isSignIn));

		Link loginLink = new Link("loginLink") {

			@Override
			public void onClick() {
				setResponsePage(LoginPage.class);
			}
		};
		add(loginLink.setVisible(!isSignIn));

		Link signupLink = new Link("signupLink") {

			@Override
			public void onClick() {
				setResponsePage(SignupPage.class);
			}
		};
		add(signupLink.setVisible(!isSignIn));

		Link<Void> profileLink = new Link<Void>("profile") {

			public void onClick() {
				setResponsePage(new ProfilePage());
			}
		};
		profileLink.setVisible(loggedUser != null && !loggedUser.getIsAdmin());
		add(profileLink);
		
		Link<Void> historyLink = new Link<Void>("history") {
			
			public void onClick() {
				setResponsePage(new ShowOrdersPage());
			}
		};
		historyLink.setVisible(loggedUser != null);
		add(historyLink);

		Link<Void> addRestaurantLink = new Link<Void>("addRestaurant") {

			public void onClick() {
				setResponsePage(new RegisterRestaurantPage());
			}
		};
		addRestaurantLink.setVisible(loggedUser != null && loggedUser.getIsAdmin());
		add(addRestaurantLink);
		
		Link<Void> addManagerLink = new Link<Void>("addManager") {

			public void onClick() {
				setResponsePage(new AddManagerPage());
			}
		};
		addManagerLink.setVisible(loggedUser != null && loggedUser.getIsAdmin());
		add(addManagerLink);
		
		Link<Void> addDishLink = new Link<Void>("addDish") {

			public void onClick() {
				setResponsePage(new AddDishPage());
			}
		};
		addDishLink.setVisible(loggedUser != null && loggedUser.getIsManager());
		add(addDishLink);
	}

	private void initialize() {
		boolean isSignIn = BaseSession.get().isSignedIn();
		if (isSignIn) {
			loggedUser = BaseSession.get().getUser();

		}

	}

}
