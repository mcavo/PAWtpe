package ar.edu.itba.it.paw.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.services.ManagerService;

public class AddDish extends HttpServlet {
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/addDish.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String section = request.getParameter("section");
		String dish = request.getParameter("dish");
		String price = request.getParameter("price");
		String desc = request.getParameter("description");
		
		ManagerService.addDish(section,dish,price,desc);
		
		
		request.getRequestDispatcher("/WEB-INF/jsp/addDish.jsp").forward(request, response);
	}

}
