package ar.edu.itba.it.paw;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Homepage extends HttpServlet{

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		UserManager userManager = new SessionUserManager(request);
		if (userManager.existsUser()) {
			//email = userManager.getEmail();
		}else{
			
			
		}
		//response.setContentType("text/html");*/

		request.setAttribute("email", request.getAttribute("email"));
		request.getRequestDispatcher("/WEB-INF/jsp/homepage.jsp").forward(request, response);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
