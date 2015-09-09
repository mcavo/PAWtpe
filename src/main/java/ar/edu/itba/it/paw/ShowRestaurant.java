package ar.edu.itba.it.paw;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.models.Menu;
import ar.edu.itba.it.paw.models.Restaurant;
import ar.edu.itba.it.paw.models.Section;
import ar.edu.itba.it.paw.services.RestService;

public class ShowRestaurant extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		Restaurant r = RestService.getRestaurant(name);
		Menu menu = r.getMenu();
		List<Section> sections = menu.getSections();

		req.setAttribute("name", name);
		req.setAttribute("sections", sections);
		req.setAttribute("score", r.getScore());
		req.getRequestDispatcher("/WEB-INF/jsp/showRestaurant.jsp").forward(req, resp);
	}
}
