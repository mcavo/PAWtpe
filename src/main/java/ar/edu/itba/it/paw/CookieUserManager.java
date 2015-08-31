package ar.edu.itba.it.paw;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUserManager implements UserManager {
	private static String USR_ID = "user";
	private static String PSW = "password";
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public CookieUserManager ( HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}
	
	public boolean existsUser() {
		if (getCookie(USR_ID) != null) {
			return true;
		} else {
			String name = request.getParameter(USR_ID);
			String sign = request.getParameter(PSW);
			return name != null && sign != null;
		}
	}
	
	public String getUser() {
		return getByID(USR_ID);
	}
	
	public void setUser(String usr, String psw) {
		setCookie(USR_ID, usr, false);
		setCookie(PSW, psw, false);
	}
	
	public void resetUser(String usr) {
		setCookie(USR_ID, usr, true);
		setCookie(PSW, "", true);
	}
	
	private void setCookie(String name, String value, boolean delete) {
		Cookie cookie = new Cookie(name, value);
		if (delete) {
			cookie.setMaxAge(0);
		}
		response.addCookie(cookie);
	}
	
	private Cookie getCookie(String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for( Cookie c: cookies) {
				if (c.getName().equals(name)) {
					return c;
				}
			}
		}
		return null;
	}
	
	private String getByID(String id) {
		Cookie c = getCookie(id);
		if (c != null) {
			return c.getValue();
		} else {
			return request.getParameter(id);
		}
	}
}

