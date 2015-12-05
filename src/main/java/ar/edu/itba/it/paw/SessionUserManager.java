package ar.edu.itba.it.paw;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ar.edu.itba.it.paw.domain.users.User;

public class SessionUserManager implements UserManager{

	private static String USR= "usr";
	private HttpServletRequest request;
	
	public SessionUserManager(HttpServletRequest request) {
		this.request = request;
	}
	
	public boolean existsUser() {
		HttpSession session = request.getSession();
		//System.out.println("request usr id="+request.getParameter(USR_ID) != null );
		return ((session.getAttribute(USR) != null ) ||
				(request.getParameter(USR) != null ));
	}
	
	public User getUser() {
		HttpSession session = request.getSession();
		return (User) session.getAttribute("usr");
	}
	
	
	public boolean setUser(User usr) {
		if(usr==null)
			return false;
		HttpSession session = request.getSession();
		session.setAttribute(USR, usr);
		return true;
	}
	
	public void resetUser(String usr) {
		HttpSession session = request.getSession();
		session.setAttribute(USR, null);
		//session.setAttribute(PSW, null);
	}
	
	@SuppressWarnings("unused")
	private String getByID(String id) {
		HttpSession session = request.getSession();
		String value = (String)session.getAttribute(id);
		if (value != null) {
			return value;
		} else {
			return request.getParameter(id);
		}
	}

	public String getUserId() {
		HttpSession session = request.getSession();
		return (String) session.getAttribute(USR);
	}

}