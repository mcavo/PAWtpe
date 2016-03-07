package ar.edu.itba.it.paw.web.base;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.web.DemoWicketSession;

public class BasePage extends WebPage {

	private static final long serialVersionUID = 1L;

	//@SpringBean
	//private UserRepo users;

	public BasePage() {

		// User user = getUser();

		// tenes que agregar todos los componentes de la barra e ir jugando con
		// la visibilidad de las cosas segun lo que quieras mostrar o no
		add(new Link<Void>("tripHotel") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				//setResponsePage(HomePage.class);
			}

		});

		add(new Link<Void>("findHotel") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				//setResponsePage(new FindHotelPage(null));
			}

		});
		add(new Link<Void>("fastFind") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				//setResponsePage(new FastFindHotelPage());
			}

		});
		Link<Void> addHotel = new Link<Void>("addHotel") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				//setResponsePage(AddHotelPage.class);
			}

			@Override
			public boolean isVisible() {
//				User user = getUser();
//				if (user == null)
//					return false;
//				return user.isAdmin();
				return false;
			}

		};

		Link<Void> viewUsers = new Link<Void>("viewUsers") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				//setResponsePage(UserListPage.class);
			}

			@Override
			public boolean isVisible() {
//				User user = getUser();
//				if (user == null)
//					return false;
//				return user.isAdmin();
				return false;
			}
		};

		Link<Void> profile = new Link<Void>("profile") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				//User user = getUser();
				//setResponsePage(new UserPage(user));
			}

			@Override
			public boolean isVisible() {
				//return getUser() != null;
				return false;
			}

		};

		Label mailLabel = new Label("mail", new PropertyModel<String>(
				DemoWicketSession.get(), "mail")) {

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isVisible() {
				//return getUser() != null;
				return false;
			}
		};

		add(mailLabel);

		Link<Void> logout = new Link<Void>("logout") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
//				User user = getUser();
//				user.resetToken();
//				CookieService cookieService = ((MyApp) MyApp.get())
//						.getCookieService();
//				cookieService.removeSessionCookies(getRequest(), getResponse());
//				getDemoWicketSession().signOut();
//				setResponsePage(getApplication().getHomePage());
			}

			@Override
			public boolean isVisible() {
				//return getUser() != null;
				return false;
			}
		};

		Link<Void> login = new Link<Void>("login") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				//setResponsePage(LoginPage.class);
			}

			@Override
			public boolean isVisible() {
				//return getUser() == null;
				return false;
			}

		};
		Link<Void> signup = new Link<Void>("signup") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				//setResponsePage(SignUpPage.class);
			}

			@Override
			public boolean isVisible() {
				//return getUser() == null;
				return false;
			}

		};

		add(viewUsers);
		add(addHotel);
		add(profile);
		add(logout);
		add(login);
		add(signup);

	}

	protected DemoWicketSession getDemoWicketSession() {
		return (DemoWicketSession) getSession();
	}

//	protected User getUser() {
//		User user = null;
//		if (getDemoWicketSession().isSigned()) {
//			user = users.get(getDemoWicketSession().getMail());
//		}
//		return user;
//	}

}
