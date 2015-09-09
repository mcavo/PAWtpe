package ar.edu.itba.it.paw;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowRestaurant extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		req.setAttribute("name", name);
		req.getRequestDispatcher("/WEB-INF/jsp/showRestaurant.jsp").forward(req, resp);
	}
}
