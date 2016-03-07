package ar.edu.itba.it.paw.web.common;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.Cookie;

import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.http.WebRequest;
import org.apache.wicket.request.http.WebResponse;

public class CookieService {

	private static final String REMEMBER_ME_LOGIN = "remember_me_login";
	private static final String REMEMBER_ME_TOKEN = "remember_me_token";
	private static final int EXPIRE_TIME_IN_DAYS = 10;

	private Cookie loadCookie(Request request, String cookieName) {

		List<Cookie> cookies = ((WebRequest) request).getCookies();

		if (cookies == null) {
			return null;
		}

		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(cookieName)) {
				return cookie;
			}
		}

		return null;
	}

	public Cookie loadLoginCookie(Request request) {
		return loadCookie(request, REMEMBER_ME_LOGIN);
	}

	public Cookie loadTokenCookie(Request request) {
		return loadCookie(request, REMEMBER_ME_TOKEN);
	}

	private void saveCookie(Response response, String cookieName,
			String cookieValue, int expiryTimeInDays) {
		Cookie cookie = new Cookie(cookieName, cookieValue);
		cookie.setPath("/");
		cookie.setMaxAge((int) TimeUnit.DAYS.toSeconds(expiryTimeInDays));
		((WebResponse) response).addCookie(cookie);
	}

	public void saveLogincookie(Response response, String cookieValue) {
		saveCookie(response, REMEMBER_ME_LOGIN, cookieValue,
				EXPIRE_TIME_IN_DAYS);
	}

	public void saveTokencookie(Response response, String cookieValue) {
		saveCookie(response, REMEMBER_ME_TOKEN, cookieValue,
				EXPIRE_TIME_IN_DAYS);
	}

	private void removeCookieIfPresent(Request request, Response response,
			String cookieName) {
		Cookie cookie = loadCookie(request, cookieName);

		if (cookie != null) {
			cookie.setValue("");
			cookie.setMaxAge(0);
			((WebResponse) response).addCookie(cookie);
		}
	}
	
	public void removeSessionCookies(Request request, Response response){
		removeCookieIfPresent(request, response, REMEMBER_ME_LOGIN);
		removeCookieIfPresent(request, response, REMEMBER_ME_TOKEN);
	}

}
