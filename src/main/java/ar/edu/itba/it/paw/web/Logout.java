package ar.edu.itba.it.paw.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.SessionUserManager;
import ar.edu.itba.it.paw.UserManager;

public class Logout extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserManager userManager = new SessionUserManager(request);
		if (userManager.existsUser()) {
			userManager.resetUser(null);
		}
		request.getRequestDispatcher("/WEB-INF/jsp/logout.jsp").forward(request, response);
	}
}
