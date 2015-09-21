package ar.edu.itba.it.paw;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.models.Credential;
import ar.edu.itba.it.paw.models.User;
import ar.edu.itba.it.paw.services.UserService;


@SuppressWarnings("serial")
public class Login extends HttpServlet{

	private String email;
	private String pwd;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		UserManager userManager = new SessionUserManager(request);
		if (userManager.existsUser()) {
			response.sendRedirect("homepage");
			return;
		}
		//response.setContentType("text/html");
		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = null;
		email = req.getParameter("email");
		pwd = req.getParameter("pwd");
		
		Credential cred = UserService.getUserCredentials(email, pwd);
		if(cred == null){
			resp.sendRedirect(resp.encodeRedirectURL("login"));
			return;
		}
		user = UserService.getUserById(cred);
		
		UserManager userManager = new SessionUserManager(req);
		userManager.setUser(user);
		resp.sendRedirect("homepage");
	}
}
