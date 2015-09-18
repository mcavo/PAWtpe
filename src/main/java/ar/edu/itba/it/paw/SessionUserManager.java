package ar.edu.itba.it.paw;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUserManager implements UserManager{

	private static String USR_ID = null;
	//private static String PSW = "password";
	private HttpServletRequest request;
	
	public SessionUserManager(HttpServletRequest request) {
		this.request = request;
	}
	
	public boolean existsUser() {
		HttpSession session = request.getSession();
		//System.out.println("request usr id="+request.getParameter(USR_ID) != null );
		return ((session.getAttribute(USR_ID) != null ) ||
				(request.getParameter(USR_ID) != null ));
	}
	
	public String getUser() {
		return getByID(USR_ID);
	}
	
	
	public void setUser(String usrId) {
		HttpSession session = request.getSession();
		session.setAttribute(USR_ID, usrId);
	}
	
	public void resetUser(String usr) {
		HttpSession session = request.getSession();
		session.setAttribute(USR_ID, null);
		//session.setAttribute(PSW, null);
	}
	
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
		return (String) session.getAttribute(USR_ID);
	}
}