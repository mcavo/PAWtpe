package ar.edu.itba.it.paw.web.common;

import javax.servlet.http.Cookie;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.web.DemoWicketSession;

public class SessionProvider {

//	@SpringBean
//	private UserRepo users;

	private CookieService cookieService;

	public SessionProvider(CookieService cookieService) {
		this.cookieService = cookieService;
		Injector.get().inject(this);
	}

	public WebSession createNewSession(Request request) {
		DemoWicketSession session = new DemoWicketSession(request);

		Cookie loginCookie = cookieService.loadLoginCookie(request);
		Cookie tokenCookie = cookieService.loadTokenCookie(request);

		if (loginCookie != null && tokenCookie != null) {
			String email = loginCookie.getValue();
			String token = tokenCookie.getValue();
//			User user = users.get(email);
//			if (user != null && user.validateToken(token)) {
//				session.setMail(email);
//			}
		}
		return session;
	}
}
