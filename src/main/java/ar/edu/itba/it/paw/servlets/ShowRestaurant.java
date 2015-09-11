package ar.edu.itba.it.paw.servlets;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.models.Address;
import ar.edu.itba.it.paw.models.Menu;
import ar.edu.itba.it.paw.models.Restaurant;
import ar.edu.itba.it.paw.models.Section;
import ar.edu.itba.it.paw.models.User;
import ar.edu.itba.it.paw.services.RestService;

public class ShowRestaurant extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//LO TIENE QUE TRAER DEL FILTRO!
		User usr = new User(null, null, null, false, null, null);
		//----------------------
		
		String name = req.getParameter("name");
		String address = req.getParameter("addr");
		Restaurant r = RestService.getRestaurant(name, address);
		Menu menu = r.getMenu();
		List<Section> sections = menu.getSections();

		req.setAttribute("name", name);
		req.setAttribute("sections", sections);
		req.setAttribute("score", r.getScore());
		req.setAttribute("okToQualify", RestService.canQualify(r, usr));
		req.getRequestDispatcher("/WEB-INF/jsp/filtered/showRestaurant.jsp").forward(req, resp);
	}
}
