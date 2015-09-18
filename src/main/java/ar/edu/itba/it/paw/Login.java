package ar.edu.itba.it.paw;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.models.User;
import ar.edu.itba.it.paw.services.UserService;


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
		
		String id = UserService.getUserId(email, pwd);
		if(id.equals("-1")){
			resp.sendRedirect(resp.encodeRedirectURL("login"));
			return;
		}
		user = UserService.getUserById(id);
		
		UserManager userManager = new SessionUserManager(req);
		userManager.setUser(String.valueOf(id));
		resp.sendRedirect("homepage");
	}
}
